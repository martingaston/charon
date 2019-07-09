package com.github.martingaston.application;

import java.nio.charset.StandardCharsets;

class App {
  private Connection connection;

  public App(Connection connection) {
    this.connection = connection;
  }

  public static void main(String[] args) {
    System.out.println("Hello World!");
  }

  public void listen() {
    Client client = connection.awaitClient();

    byte[] request = client.receive();
    String[] parsedRequest = new String(request, StandardCharsets.UTF_8).split("\r\n\r\n");
    byte[] response = ("HTTP/1.1 200 OK\r\n\r\n" + parsedRequest[1]).getBytes();

    client.send(response);
  }
}
