package com.example.facebookdata.controller;

import com.example.facebookdata.service.FacebookService;
import com.example.facebookdata.model.UserData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class UserDataController {
    private final FacebookService service;

    public UserDataController(FacebookService service) {
        this.service = service;
    }

    @RequestMapping("/checkprofile")
    public UserData getProfileData(@RequestParam("id") String id) throws IOException {
        return service.getUserDataByProfileId(id);
    }
}
