package com.github.martingaston.application.http;

import com.github.martingaston.application.Client;

import java.io.IOException;

public class Request {
    private String method;
    private String uri;
    private String version;
    private String body;

    public Request(Client client) throws IOException {
        parseRequestLine(client.receive());

        String headers = client.receive();
        while (!headers.equals("")) {
            headers = client.receive();
        }

        this.body = client.receiveBody();

    }

    public String method() {
        return this.method;
    }

    public String uri() {
        return this.uri;
    }

    public String protocol() {
        return this.version;
    }

    public String body() {
        return this.body;
    }

    private void parseRequestLine(String requestLine) {
        String[] separatedRequestLine = requestLine.split(" ");
        this.method = separatedRequestLine[0];
        this.uri = separatedRequestLine[1];
        this.version = separatedRequestLine[2];
    }
}
