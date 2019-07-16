package com.github.martingaston.application.routes;

import com.github.martingaston.application.http.URI;
import com.github.martingaston.application.http.Verbs;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

@DisplayName("A Routes class")
class RoutesTest {
    private Routes routes;

    @BeforeEach
    void newRoute() {
        routes = new Routes();
    }

    @DisplayName("Can register a route")
    @Test
    void canAddPostRoute() {
        routes.post(URI.from("/refactor_echo_body"), new HandleEchoBody());
        assertThat(routes.isValid(Verbs.POST, URI.from("/refactor_echo_body"))).isTrue();
    }

    @DisplayName("Can register multiple routes to one path")
    @Test
    void canAddMultipleRoutes() {
        routes.post(URI.from("/refactor_echo_body"), new HandleEchoBody());
        routes.get(URI.from("/refactor_echo_body"), new HandleEchoBody());
        assertThat(routes.isValid(Verbs.GET, URI.from("/refactor_echo_body"))).isTrue();
        assertThat(routes.isValid(Verbs.POST, URI.from("/refactor_echo_body"))).isTrue();
    }

    @DisplayName("Knows an invalid path")
    @Test
    void knowsAnInvalidPath() {
        routes.post(URI.from("/correct"), new HandleEchoBody());
        assertThat(routes.isValid(Verbs.POST, URI.from("/wrong"))).isFalse();
    }

    @DisplayName("Knows an invalid method")
    @Test
    void knowsAnInvalidMethod() {
        routes.get(URI.from("/get_content"), new HandleEchoBody());
        assertThat(routes.isValid(Verbs.POST, URI.from("/get_content"))).isFalse();
    }
}