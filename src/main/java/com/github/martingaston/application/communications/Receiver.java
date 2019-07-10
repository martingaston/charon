package com.github.martingaston.application.communications;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Receiver {
    private BufferedReader buffered;

    public Receiver(InputStream stream) {
        this.buffered = new BufferedReader(new InputStreamReader(stream));
    }

    public String receiveLine() throws IOException {
        return buffered.readLine();
    }

    public byte[] receiveWhileReady() throws IOException {
        ArrayList<Byte> list = new ArrayList<>();

        int data;
        while((data = buffered.read()) != -1) {
            list.add((byte) data);
            if(!buffered.ready()) {
                break;
            }
        }

        byte[] result = new byte[list.size()];

        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }

        return result;
    }
}
