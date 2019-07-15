package com.github.martingaston.application.http;

public class Router {
    private PathHandler routes;

    public Router() {
        routes = new PathHandler();
    }

    public Response respond(Request request) {
        Response response;
        Headers headers = new Headers();
        Body body;

        var methodHandler = new MethodHandler();
        methodHandler.add(Verbs.POST, new HandleEchoBody());
        routes.add(URI.from("/refactor_echo_body"), methodHandler);

        headers.add("Connection", "close");

        if(routes.isValidPath(request.uri())) {
            var methods = routes.get(request.uri());

            if(methods.isValidMethod(request.method())) {
                var handler = methods.get(request.method());

                return handler.handle(request);
            }
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
