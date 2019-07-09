package com.github.martingaston.application;

import java.nio.charset.StandardCharsets;

public class Connection {
    private byte[] request;

    public void send(byte[] contents) {
        this.request = contents;
    }

    public byte[] received() {
        String[] parsedRequest = new String(request, StandardCharsets.UTF_8).split("\r\n\r\n");
        return ("HTTP/1.1 200 OK\r\n\r\n" + parsedRequest[1]).getBytes();
    }
}
