package com.github.martingaston.application.http;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("A Router class")
class RouterTest {
    private Router router = new Router();

    @DisplayName("With a valid POST request")
    @Nested
    class canRouteValidPOST {
        private Request request;
        private Response response;

        @BeforeEach
        void init() {
            request = new Request(new RequestLine(Verbs.POST, URI.from("/echo_body"), Version.V1POINT1), new Headers(), Body.from("It is a truth universally acknowledged..."));
            response = router.respond(request);
        }

        @DisplayName("Returns a 200 status code")
        @Test
        void returns200StatusCode() {
            assertThat(response.status()).isEqualTo(Status.OK);
        }

        @DisplayName("Echoes the Request body")
        @Test
        void echoesRequestBody() {
            assertThat(response.body()).isEqualTo(Body.from("It is a truth universally acknowledged..."));
        }
    }
}
