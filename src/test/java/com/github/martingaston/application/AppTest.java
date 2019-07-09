package com.github.martingaston.application;

import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.*;

@DisplayName("The App class")
class AppTest {
    @DisplayName("POST /echo_body with body 'some body' returns 200 with body 'some body'")
    @Test
    void simplePostRequest() {
        byte[] request = "GET /echo_body\r\n\r\nsome body".getBytes();
        byte[] response = "HTTP/1.1 200 OK\r\n\r\nsome body".getBytes();

        var connection = new Connection(request);
        var app = new App(connection);
        app.listen();

        assertThat(connection.received()).isEqualTo(response);
    }

    @DisplayName("POST /echo_body with body 'i wanna dance with some body' returns 200 with body 'i wanna dance with some body'")
    @Test
    void simplePostRequestDanceWithSomeBody() {
        byte[] request = "GET /echo_body\r\n\r\ni wanna dance with some body".getBytes();
        byte[] response = "HTTP/1.1 200 OK\r\n\r\ni wanna dance with some body".getBytes();

        var connection = new Connection(request);
        var app = new App(connection);
        app.listen();

        assertThat(connection.received()).isEqualTo(response);
    }

    @DisplayName("Will wait for a client to connect")
    @Test
    void waitsForClient() {
        byte[] request = "GET /echo_body\r\n\r\nsome body".getBytes();

        var connection = new Connection(request);
        var app = new App(connection);
        app.listen();

        assertThat(connection.awaitClientCalledXTimes()).isEqualTo(1);
    }
}
