package com.github.martingaston.application.http;

import com.github.martingaston.application.routes.Routes;

public class Router {
    private Routes routes;

    public Router(Routes routes) {
        this.routes = routes;
    }

    public Response respond(Request request) {
        Response.Builder response = createDefaultResponse();

        if (invalidRequest(request)) {
            return sendBadRequestResponse(response);
        }

        if (invalidPath(request, routes)) {
            return sendNotFoundResponse(response);
        }

        if (optionsRequest(request)) {
            return sendOptionsResponse(request, response, routes);
        }

        if (invalidMethod(request, routes)) {
            return sendMethodNotFoundResponse(request, response, routes);
        }

        handleValidRequest(request, response, routes);

        if (headRequest(request)) {
            response.body(Body.from(""));
        }

        return response.build();
    }

    private boolean invalidRequest(Request request) {
        return request.method() == Verbs.INVALID || !request.hasHeader("Host") || request.protocol() == Version.INVALID;
    }

    private Response.Builder createDefaultResponse() {
        return new Response.Builder(Status.OK)
                    .addHeader("Connection", "close");
    }

    private Response sendBadRequestResponse(Response.Builder response) {
        response.status(Status.BAD_REQUEST);
        return response.build();
    }
    private static void handleValidRequest(Request request, Response.Builder response, Routes routes) {
        routes.handler(request).handle(request, response);
        response.status(Status.OK);
        response.addHeader("Content-Length", response.bodyLength());
    }

    private static Response sendMethodNotFoundResponse(Request request, Response.Builder response, Routes routes) {
        response.status(Status.METHOD_NOT_ALLOWED);
        response.addHeader("Allow", routes.validAtPath(request));
        return response.build();
    }

    private static Response sendNotFoundResponse(Response.Builder response) {
        response.status(Status.NOT_FOUND);
        return response.build();
    }

    private static Response sendOptionsResponse(Request request, Response.Builder response, Routes routes) {
        response.addHeader("Allow", routes.validAtPath(request));
        response.body(Body.from(""));
        return response.build();
    }

    private static boolean headRequest(Request request) {
        return request.method() == Verbs.HEAD;
    }

    private static boolean invalidMethod(Request request, Routes routes) {
        return !routes.isValidMethod(request.method(), request.uri());
    }

    private static boolean optionsRequest(Request request) {
        return request.method() == Verbs.OPTIONS;
    }

    private static boolean invalidPath(Request request, Routes routes) {
        return !routes.isValidPath(request.uri());
    }
}
