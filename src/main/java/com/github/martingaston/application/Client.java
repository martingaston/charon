package com.github.martingaston.application;

public class Client {
    private byte[] in;
    private byte[] out;

    public Client(byte[] out) {
        this.in = "".getBytes();
        this.out = out;
    }

    public byte[] receive() {
        return this.out;
    }

    public void send(byte[] response) {
        this.in = response;
    }

    public byte[] received() {
        return this.in;
    }
}
