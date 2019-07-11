package com.github.martingaston.application.http;

import com.github.martingaston.application.Client;

import java.io.IOException;

public class RequestParser {
    private RequestParser() {

    }

    public static Request from(Client client) throws IOException {
        RequestLine requestLine = parseRequestLine(client);
        stripHeaders(client);
        Body body = parseBody(client);

        return new Request(requestLine, body);
    }

    private static RequestLine parseRequestLine(Client client) throws IOException {
        String[] separatedRequestLine = client.receive().split(" ");

        return new RequestLine(
                Verbs.from(separatedRequestLine[0]),
                URI.from(separatedRequestLine[1]),
                Version.from(separatedRequestLine[2])
        );
    }

    private static void stripHeaders(Client client) throws IOException {
        String headers = client.receive();
        while (!headers.equals("")) {
            headers = client.receive();
        }
    }

    private static Body parseBody(Client client) throws IOException {
        return Body.from(client.receiveBody());
    }
}
