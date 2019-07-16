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

        routes.post(URI.from("/echo_body"), req -> new Response.Builder(Status.OK)
                .addHeader("Connection", "close")
                .body(Body.from(req.body().toString()))
                .build());

        routes.head(URI.from("/simple_get"), req -> new Response.Builder(Status.OK).addHeader("Connection", "close").build());

        routes.head(URI.from("/get_with_body"), req -> new Response.Builder(Status.OK).addHeader("Connection", "close").build());

        routes.get(URI.from("/simple_get"), req -> new Response.Builder(Status.OK).addHeader("Connection", "close").build());

        return routes.handler(request).handle(request);
    }
}
