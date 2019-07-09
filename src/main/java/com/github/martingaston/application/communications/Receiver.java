package com.github.martingaston.application.communications;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Receiver {
    private BufferedReader buffered;

    public Receiver(InputStream stream) {
        this.buffered = new BufferedReader(new InputStreamReader(stream));
    }

    public String receive() throws IOException {
        return buffered.readLine();
    }
}
