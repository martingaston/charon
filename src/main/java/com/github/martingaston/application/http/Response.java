package com.github.martingaston.application.http;

public class Response {
    private Version version;
    private Status status;
    private Headers headers;
    private Body body;

    Response(Status status, Headers headers, Body body) {
        this.version = Version.V1POINT1;
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
