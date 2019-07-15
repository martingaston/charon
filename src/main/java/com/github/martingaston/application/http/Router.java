package com.github.martingaston.application.http;

import java.util.concurrent.ConcurrentHashMap;

public class Router {
    private Request request;

    public Router(Request request) {
        this.request = request;
    }

    public Response respond() {
        Response response;
        Headers headers = new Headers();
        Body body;

        ConcurrentHashMap<URI, Handler> routes = new ConcurrentHashMap<>();
        routes.put(URI.from("/refactor_echo_body"), new HandleEchoBody());

        headers.add("Connection", "close");

        if(routes.containsKey(request.uri())) {
            var handler = routes.get(request.uri());
            return handler.handle(request);
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
