package com.github.martingaston.application;

import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.*;

@DisplayName("The App class")
class AppTest {
    private Connection connection;
    private App app;

    @BeforeEach void createApp() {
        connection = new Connection();
        app = new App(connection);
    }

    @DisplayName("POST /echo_body with body 'some body' returns 200 with body 'some body'")
    @Test
    void simplePostRequest() {
        byte[] request = "GET /echo_body\r\n\r\nsome body".getBytes();
        byte[] response = "HTTP/1.1 200 OK\r\n\r\nsome body".getBytes();

        connection.setRequest(request);
        app.listen();

        assertThat(connection.received()).isEqualTo(response);
    }

    @DisplayName("POST /echo_body with body 'i wanna dance with some body' returns 200 with body 'i wanna dance with some body'")
    @Test
    void simplePostRequestDanceWithSomeBody() {
        byte[] request = "GET /echo_body\r\n\r\ni wanna dance with some body".getBytes();
        byte[] response = "HTTP/1.1 200 OK\r\n\r\ni wanna dance with some body".getBytes();

        connection.setRequest(request);
        app.listen();

        assertThat(connection.received()).isEqualTo(response);
    }

    @DisplayName("Will wait for a client to connect")
    @Test
    void waitsForClient() {
        byte[] request = "GET /echo_body\r\n\r\nsome body".getBytes();

        connection.setRequest(request);
        app.listen();

        assertThat(connection.awaitClientCalledXTimes()).isEqualTo(1);
    }
}
