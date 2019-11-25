package com.example.facebookdata.model;

public class UserData {
    private String name;
    private String photo;

    public UserData(String name, String photo) {
        this.name = name;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public String getPhoto() {
        return photo;
    }
}
