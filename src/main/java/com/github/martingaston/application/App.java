package com.github.martingaston.application;

class App {
  Connection connection;

  public App(Connection connection) {
    this.connection = connection;
  }

  public static void main(String[] args) {
    System.out.println("Hello World!");
  }

  public void listen() {
    connection.awaitClient();
  }
}
