package com.github.martingaston.application.http;

import com.github.martingaston.application.Client;

import java.io.IOException;

public class Request {
    String method;
    String uri;
    String version;
    String body;

    public Request(Client client) throws IOException {
        parseRequestLine(client.receive());

        String headers = client.receive();
        while (!headers.equals("")) {
            headers = client.receive();
        }

        this.body = client.receiveBody();

    }

    public String method() {
        return "POST";
    }

    public String uri() {
        return "/echo_body";
    }

    public String protocol() {
        return "HTTP/1.1";
    }

    public String body() {
        return "some body";
    }

    private void parseRequestLine(String requestLine) {

    }
}
