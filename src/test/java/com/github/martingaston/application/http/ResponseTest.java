package com.github.martingaston.application.http;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

@DisplayName("A Response class")
class ResponseTest {
    @Nested
    @DisplayName("Made from a valid POST request with a body and no headers")
    class fromValidPostRequest {
        @DisplayName("Will return its information")
        @Test
        void returnsInformation() {
            Response response = new Response(Status.OK, new Headers(), Body.from("Some body"));
            assertThat(response.version()).isEqualTo(Version.V1POINT1);
            assertThat(response.status()).isEqualTo(Status.OK);
            assertThat(response.body()).isEqualTo(Body.from("Some body"));
        }
    }
}