package com.example.facebookdata.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String id) {
        super(String.format("User Profile ID '%s' protected or incorrect", id));
    }
}
