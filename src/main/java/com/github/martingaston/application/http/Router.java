package com.github.martingaston.application.http;

import com.github.martingaston.application.routes.HandleEchoBody;
import com.github.martingaston.application.routes.Routes;


public class Router {
    private Routes routes;

    public Router() {
        routes = new Routes();
    }

    public Response respond(Request request) {
        Response response;
        Headers headers = new Headers();
        Body body;

        routes.post(URI.from("/refactor_echo_body"), new HandleEchoBody());

        headers.add("Connection", "close");

        if(routes.isValid(request.method(), request.uri())) {
            return routes.handler(request).handle(request);
        }

        switch (request.method()) {
            case GET:
                headers.add("Content-Length", 0);
                body = Body.from("");
                response = new Response(Status.OK, headers, body);
                break;
            case POST:
                headers.add("Content-Length", request.bodyContentLength());
                body = Body.from(request.body().toString());
                response = new Response(Status.OK, headers, body);
                break;
            case HEAD:
                headers.add("Content-Length", request.bodyContentLength());
                body = Body.from("");
                response = new Response(Status.OK, headers, body);
                break;
            default:
                headers.add("Allow", "GET");
                body = Body.from("");
                response = new Response(Status.METHOD_NOT_ALLOWED, headers, body);
                break;
        }

        return response;
    }
}
