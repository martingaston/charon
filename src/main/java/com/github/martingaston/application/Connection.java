package com.github.martingaston.application;

public class Connection {
    private Client client;
    private byte[] request;
    private int timesAwaitClientCalled = 0;

    public Connection(byte[] request) {
        this.request = request;
    }

    public byte[] received() {
        return this.client.received();
    }

    public int awaitClientCalledXTimes() {
        return timesAwaitClientCalled;
    }

    public Client awaitClient() {
        timesAwaitClientCalled += 1;
        this.client = new Client(this.request);
        return this.client;
    }
}
