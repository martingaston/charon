package com.github.martingaston.application;

import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.*;

@DisplayName("The App class")
class AppTest {
  @DisplayName("Has a functioning test runner")
  @Test
  void testSimpleAddition() {
    assertThat(1+1).isEqualTo(2);
  }

  @DisplayName("POST /echo_body with body 'some body' returns 200 with body 'some body'")
  @Test
  void simplePostRequest() {
    byte[] request = "GET /echo_body\r\n\r\nsome body".getBytes();
    byte[] response = "HTTP/1.1 200 OK\r\n\r\nsome body".getBytes();
    App app = new App();

    assertThat(app.listen(request)).isEqualTo(response);
  }

  @DisplayName("POST /echo_body with body 'i wanna dance with some body' returns 200 with body 'i wanna dance with some body'")
  @Test
  void simplePostRequestDanceWithSomeBody() {
    byte[] request = "GET /echo_body\r\n\r\ni wanna dance with some body".getBytes();
    byte[] response = "HTTP/1.1 200 OK\r\n\r\ni wanna dance with some body".getBytes();
    App app = new App();

    assertThat(app.listen(request)).isEqualTo(response);
  }
}
