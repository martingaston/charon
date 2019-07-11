package com.github.martingaston.application.http;

import com.github.martingaston.application.Client;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

@DisplayName("A Request class")
class RequestTest {
    @Nested
    @DisplayName("With a valid POST request containing a body and no headers")
    class postRequestNoHeaders {
        private ByteArrayInputStream input = new ByteArrayInputStream("POST /echo_body HTTP/1.1\r\n\r\nsome body".getBytes());
        private ByteArrayOutputStream output = new ByteArrayOutputStream();
        private Client client = new Client(input, output);
        private Request request;

        @BeforeEach
        void init() throws IOException {
            request = RequestParser.from(client);
        }

        @DisplayName("Will return a POST method")
        @Test
        void hasPostMethod() {
            assertThat(request.method()).isEqualTo(Verbs.POST);
        }

        @DisplayName("Will return a URI of /echo_body")
        @Test
        void hasEchoBodyURI() {
            assertThat(request.uri()).isEqualTo(URI.from("/echo_body"));
        }

        @DisplayName("Will return a HTTP method of HTTP/1.1")
        @Test
        void hasHttpMethod() {
            assertThat(request.protocol()).isEqualTo(Version.V1POINT1);
        }

        @DisplayName("Will return a body")
        @Test
        void returnsBody() {
            assertThat(request.body()).isEqualTo(Body.from("some body"));
        }
    }
}