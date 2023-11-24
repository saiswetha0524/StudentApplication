package com.example.StudentApplication.models;

public enum StatusEnum {
    SUCCESS("Success"),
    FAILURE("Failure");

    final String message;

    StatusEnum(String message) {
        this.message = message;
    }

    //TODO: Implement
    // fromValue input - text | return enum

}
