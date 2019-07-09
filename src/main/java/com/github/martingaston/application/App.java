package com.github.martingaston.application;

import java.io.IOException;

class App {
  private Connection connection;

  public App(Connection connection) {
    this.connection = connection;
  }

  public static void main(String[] args) {
    System.out.println("Hello World!");
  }

  public void listen() throws IOException {
    Client client = connection.awaitClient();

    String headers = client.receive();
    String emptyLine = client.receive();
    String body = client.receive();
    String response = "HTTP/1.1 200 OK\r\n\r\n" + body;

    client.send(response);
  }
}
