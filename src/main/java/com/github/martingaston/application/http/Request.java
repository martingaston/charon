package com.github.martingaston.application.http;

import com.github.martingaston.application.Client;

import java.io.IOException;

public class Request {
    private Verbs method;
    private URI uri;
    private Version version;
    private Body body;

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
        this.method = Verbs.from(separatedRequestLine[0]);
        this.uri = URI.from(separatedRequestLine[1]);
        this.version = Version.from(separatedRequestLine[2]);
    }

    private void stripHeaders(Client client) throws IOException {
        String headers = client.receive();
        while (!headers.equals("")) {
            headers = client.receive();
        }
    }

    private void parseBody(Client client) throws IOException {
        this.body = Body.from(client.receiveBody());
    }

    public int bodyContentLength() {
        return this.body.contentLength();
    }

    public Verbs method() {
        return this.method;
    }

    public URI uri() {
        return this.uri;
    }

    public Version protocol() {
        return this.version;
    }

    public Body body() {
        return this.body;
    }
}
