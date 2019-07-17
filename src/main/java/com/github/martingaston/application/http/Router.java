package com.github.martingaston.application.http;

import com.github.martingaston.application.routes.Routes;

public class Router {
    private Routes routes;

    public Router() {
        routes = new Routes();
    }

    public Response respond(Request request) {

        Response.Builder response = new Response.Builder(Status.OK)
                .addHeader("Connection", "close");

        routes.post(URI.from("/echo_body"), (req, res) -> res.body(Body.from(req.body().toString())));

        routes.head(URI.from("/simple_get"), (req, res) -> res);

        routes.head(URI.from("/get_with_body"), (req, res) -> res);

        routes.get(URI.from("/simple_get"), (req, res) -> res);

        return routes.handler(request).handle(request, response).build();
    }
}
