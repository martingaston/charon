package com.github.martingaston.application.http;

import com.github.martingaston.application.routes.Routes;

public class Router {
    private Routes routes;

    public Router() {
        routes = new Routes();
    }

    public Response respond(Request request) {
        Response.Builder response = createDefaultResponse();

        routes.post(URI.from("/echo_body"), (req, res) -> res.body(Body.from(req.body().toString())));

        routes.head(URI.from("/get_with_body"), (req, res) -> res);

        routes.get(URI.from("/simple_get"), (req, res) -> res);

        routes.get(URI.from("/method_options"), (req, res) -> res);

        routes.get(URI.from("/method_options2"), (req, res) -> res);

        routes.post(URI.from("/method_options2"), (req, res) -> res);

        routes.put(URI.from("/method_options2"), (req, res) -> res);

        return handle(request, response);
    }

    private Response.Builder createDefaultResponse() {
        return new Response.Builder(Status.OK)
                    .addHeader("Connection", "close");
    }

    private Response handle(Request request, Response.Builder response) {
       return routes.handle(request, response);
    }
}
