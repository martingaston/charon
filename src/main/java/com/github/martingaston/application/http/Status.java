package com.github.martingaston.application.http;

public enum Status {
    OK ("200 OK"),
    METHOD_NOT_ALLOWED("405 Method Not Allowed");

    private String response;

    Status(String response) {
        this.response = response;
    }

    public String response() {
        return response;
    }
}