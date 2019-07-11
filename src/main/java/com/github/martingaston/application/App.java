package com.github.martingaston.application;

import com.github.martingaston.application.http.Request;
import com.github.martingaston.application.http.RequestParser;
import com.github.martingaston.application.http.Response;
import com.github.martingaston.application.transport.Connection;
import com.github.martingaston.application.transport.Server;

import java.io.IOException;

class App {
    private Server connection;
    private Runner running;

    public App(Server connection) {
        this(connection, new LiveRunning());
    }

    public App(Server connection, Runner running) {
        this.connection = connection;
        this.running = running;
    }

    public static void main(String[] args) throws IOException {
        var connection = new Connection(5000);
        var app = new App(connection);

        app.listen();
    }

    public void listen() throws IOException {
        while (running.isRunning()) {
            Client client = connection.awaitClient();
            Request request = RequestParser.from(client);
            Response response = new Response(request);

            client.send(response.respond());
        }
    }
}

