package com.github.martingaston.application.http;

import com.github.martingaston.application.routes.Routes;

public class Router {
    private Routes routes;

    public Router() {
        routes = new Routes();
    }

    public Response respond(Request request) {
        Headers headers = new Headers();

        headers.add("Connection", "close");

        routes.post(URI.from("/echo_body"), req -> {
            headers.add("Content-Length", req.bodyContentLength());
            return new Response(Status.OK, headers, Body.from(req.body().toString()));
        });

        routes.head(URI.from("/simple_get"), req -> {
            headers.add("Content-Length", 0);
            return new Response(Status.OK, headers, Body.from(""));
        });

        routes.head(URI.from("/get_with_body"), req -> {
            headers.add("Content-Length", 0);
            return new Response(Status.OK, headers, Body.from(""));
        });

        routes.get(URI.from("/simple_get"), req -> {
            headers.add("Content-Length", 0);
            return new Response(Status.OK, headers, Body.from(""));
        });

        return routes.handler(request).handle(request);
    }
}
