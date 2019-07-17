package com.github.martingaston.application.routes;

import com.github.martingaston.application.http.*;

public class Validator {
    private Validator() {

    }

    public static Response from(Request request, Response.Builder response, Routes routes) {
        if (invalidPath(request, routes)) {
            response.status(Status.NOT_FOUND);
            return response.build();
        }

        if (optionsRequest(request)) {
            response.addHeader("Allow", routes.validAtPath(request));
            response.body(Body.from(""));
            return response.build();
        }

        if (invalidMethod(request, routes)) {
            response.status(Status.METHOD_NOT_ALLOWED);
            response.addHeader("Allow", routes.validAtPath(request));
            return response.build();
        }

        routes.handler(request).handle(request, response);

        response.status(Status.OK);
        response.addHeader("Content-Length", response.bodyLength());

        if (headRequest(request)) {
            response.body(Body.from(""));
        }

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
