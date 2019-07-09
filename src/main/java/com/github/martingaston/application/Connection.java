package com.github.martingaston.application;

public class Connection {
    private Client client;
    private byte[] request;
    private int timesAwaitClientCalled = 0;

    public void setRequest(byte[] contents) {
        this.request = contents;
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
