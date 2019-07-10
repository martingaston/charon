package com.github.martingaston.application.http;

import com.github.martingaston.application.Client;

import java.io.IOException;

public class Request {
    private String method;
    private String uri;
    private String version;
    private String body;

    public Request(Client client) throws IOException {
        parseRequest(client);
    }

    private void parseRequest(Client client) throws IOException {
        parseRequestLine(client);
        stripHeaders(client);
        parseBody(client);
    }

    private void parseRequestLine(Client client) throws IOException {
        String requestLine = client.receive();
        String[] separatedRequestLine = requestLine.split(" ");
        this.method = separatedRequestLine[0];
        this.uri = separatedRequestLine[1];
        this.version = separatedRequestLine[2];
    }

    private void stripHeaders(Client client) throws IOException {
        String headers = client.receive();
        while (!headers.equals("")) {
            headers = client.receive();
        }
    }

    private void parseBody(Client client) throws IOException {
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
}
