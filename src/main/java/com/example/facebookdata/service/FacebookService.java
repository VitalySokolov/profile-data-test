package com.example.facebookdata.service;

import com.example.facebookdata.model.UserData;
import com.example.facebookdata.exceptions.UserNotFoundException;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

@Service
public class FacebookService {

    public UserData getUserDataByProfileId(String id) throws IOException {
        String URL = "https://mbasic.facebook.com/";
        try {
            Document document = Jsoup.connect(String.format("%s%s", URL, id)).get();

            String userName = getUserName(document, id);
            String encodedImageString = getEncodedImageString(document, userName);

            return new UserData(userName, encodedImageString);
        } catch (HttpStatusException ex) {
            throw new UserNotFoundException(id);
        }
    }

    private String getEncodedImageString(Document document, String userName) throws IOException {
        String imageCssQuery = String.format("img[alt=%s]", userName);
        String imageUrl = document.selectFirst(imageCssQuery).attr("src");

        return getBase64EncodedImage(imageUrl);
    }

    private String getUserName(Document document, String id) {
        Element userNameElement = document.selectFirst("meta[property=og:title]");
        if (userNameElement == null) {
            throw new UserNotFoundException(id);
        }

        System.out.println(userNameElement.attr("content"));
        return userNameElement.attr("content");
    }

    private String getBase64EncodedImage(String imageURL) throws IOException {
        java.net.URL url = new URL(imageURL);
        InputStream is = url.openStream();
        byte[] bytes = IOUtils.toByteArray(is);
        return Base64.encodeBase64String(bytes);
    }
}
