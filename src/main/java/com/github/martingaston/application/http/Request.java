package com.github.martingaston.application.http;

public class Request {
    private RequestLine requestLine;
    private Body body;

    public Request(RequestLine requestLine, Body body) {
        this.requestLine = requestLine;
        this.body = body;
    }

    public int bodyContentLength() {
        return this.body.contentLength();
    }

    public Verbs method() {
        return this.requestLine.method();
    }

    public URI uri() {
        return this.requestLine.uri();
    }

    public Version protocol() {
        return this.requestLine.version();
    }

    public Body body() {
        return this.body;
    }
}
