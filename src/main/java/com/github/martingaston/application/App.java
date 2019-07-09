package com.github.martingaston.application;

import java.nio.charset.StandardCharsets;

class App {
  public static void main(String[] args) {
    System.out.println("Hello World!");
  }

  public byte[] listen(byte[] request) {
    String[] parsedRequest = new String(request, StandardCharsets.UTF_8).split("\r\n\r\n");
    return ("HTTP/1.1 200 OK\r\n\r\n" + parsedRequest[1]).getBytes();
  }
}
