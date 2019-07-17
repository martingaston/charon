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

    @DisplayName("With a invalid method request")
    @Nested
    class canRouteInvalidMethod {
        private Request request;
        private Response response;

        @BeforeEach
        void init() {
            request = new Request(new RequestLine(Verbs.GET, URI.from("/get_with_body"), Version.V1POINT1), new Headers(), Body.from(""));
            response = router.respond(request);
        }

        @DisplayName("Returns a 405 status code")
        @Test
        void returns405StatusCode() {
            assertThat(response.status()).isEqualTo(Status.METHOD_NOT_ALLOWED);
        }
    }

    @DisplayName("With a HEAD request on a path that exists as a GET")
    @Nested
    class respondsToHeadForGetPaths {
        private Request request;
        private Response response;

        @BeforeEach
        void init() {
            request = new Request(new RequestLine(Verbs.HEAD, URI.from("/get_example"), Version.V1POINT1), new Headers(), Body.from(""));
            response = router.respond(request);
        }

        @DisplayName("Returns a 200 status code")
        @Test
        void returnsA200StatusCode() {
            assertThat(response.status()).isEqualTo(Status.OK);
        }

        @DisplayName("Response has no body")
        @Test
        void returnsAnEmptyBody() {
            assertThat(response.body()).isEqualTo(Body.from(""));
        }

        @DisplayName("Response has a Content-Length header")
        @Test
        void returnsContentLengthHeader() {
            assertThat(response.headers().get("Content-Length")).isEqualTo("36");
        }
    }

    @DisplayName("With an invalid path request")
    @Nested
    class canRouteInvalidPath {
        @DisplayName("Returns a 404 status code")
        @Test
        void returns404StatusCode() {
            Request request = new Request(new RequestLine(Verbs.POST, URI.from("/totally_invalid_path"), Version.V1POINT1), new Headers(), Body.from("It is a truth universally acknowledged..."));
            Response response = router.respond(request);
            assertThat(response.status()).isEqualTo(Status.NOT_FOUND);
        }
    }
}
