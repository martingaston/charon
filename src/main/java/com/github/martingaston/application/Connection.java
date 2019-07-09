package com.github.martingaston.application;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class Connection {
    private ByteArrayInputStream in;
    private ByteArrayOutputStream out;
    private int timesAwaitClientCalled = 0;

    public Connection(byte[] request) {
        in = new ByteArrayInputStream(request);
        out = new ByteArrayOutputStream();
    }

    public byte[] received() {
        return out.toByteArray();
    }

    public int awaitClientCalledXTimes() {
        return timesAwaitClientCalled;
    }

    public Client awaitClient() {
        timesAwaitClientCalled += 1;
        return new Client(in, out);
    }
}
