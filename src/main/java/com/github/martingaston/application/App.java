package com.github.martingaston.application;

import com.github.martingaston.application.http.Request;
import com.github.martingaston.application.transport.Connection;
import com.github.martingaston.application.transport.Server;

import java.io.IOException;

class App {
  private Server connection;

  public App(Server connection) {
    this.connection = connection;
  }

  public static void main(String[] args) throws IOException {
      var connection = new Connection(5000);
      var app = new App(connection);

      app.listen();
  }

  public void listen() throws IOException {
    Client client = connection.awaitClient();
    Request request = new Request(client);

    String response = "HTTP/1.1 200 OK\r\n\r\n" + request.body();

    client.send(response);
  }
}

