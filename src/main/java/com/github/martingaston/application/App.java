package com.github.martingaston.application;

import com.github.martingaston.application.http.*;
import com.github.martingaston.application.transport.Connection;
import com.github.martingaston.application.transport.Port;
import com.github.martingaston.application.transport.Server;

import java.io.IOException;

class App {
    private Server connection;
    private Runner running;
    private static final String PORT_PROPERTY = "app.port";
    private static final int DEFAULT_PORT = 5000;

    public App(Server connection) {
        this(connection, new LiveRunning());
    }

    public App(Server connection, Runner running) {
        this.connection = connection;
        this.running = running;
    }

    public static void main(String[] args) throws IOException {
        var port = new Port(getPortNumber());
        var connection = new Connection(port);
        var app = new App(connection);

        app.listen();
    }

    public void listen() throws IOException {
        while (running.isRunning()) {
            Client client = connection.awaitClient();
            Request request = RequestParser.from(client);
            Router router = new Router();
            Response response = router.respond(request);

            client.send(ResponseSender.from(response));
        }
    }

    private static int getPortNumber() {
        var portProperty = System.getProperty(PORT_PROPERTY);

        if(portProperty == null) {
            return DEFAULT_PORT;
        }

        return Integer.parseInt(portProperty);
    }
}

