package com.github.martingaston.application.http;

public class Response {
    private Version version;
    private Status status;
    private Headers headers;
    private Body body;

    Response(Version version, Status status, Headers headers, Body body) {
        this.version = version;
        this.status = status;
        this.headers = headers;
        this.body = body;
    }

    public Version version() {
        return this.version;
    }

    public Status status() {
        return this.status;
    }

    public Body body() {
        return this.body;
    }
}
