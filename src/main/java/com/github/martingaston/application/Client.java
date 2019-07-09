package com.github.martingaston.application;

import com.github.martingaston.application.communications.Receiver;
import com.github.martingaston.application.communications.Sender;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Client {
    private Sender sender;
    private Receiver receiver;

    public Client(InputStream in, OutputStream out) {
        this.sender = new Sender(out);
        this.receiver = new Receiver(in);
    }

    public String receive() throws IOException {
        return receiver.receive();
    }

    public void send(String response) throws IOException {
        sender.send(response);
    }
}
