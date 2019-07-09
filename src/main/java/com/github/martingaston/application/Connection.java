package com.github.martingaston.application;

import java.nio.charset.StandardCharsets;

public class Connection {
    private byte[] request;
    private int timesAwaitClientCalled = 0;

    public void send(byte[] contents) {
        this.request = contents;
    }

    public byte[] received() {
        String[] parsedRequest = new String(request, StandardCharsets.UTF_8).split("\r\n\r\n");
        return ("HTTP/1.1 200 OK\r\n\r\n" + parsedRequest[1]).getBytes();
    }

    public int awaitClientCalledXTimes() {
        return timesAwaitClientCalled;
    }

    public void awaitClient() {
        timesAwaitClientCalled += 1;
    }
}
