package com.github.martingaston.application.routes;

import com.github.martingaston.application.http.*;

public class Validator {
    private Validator() {

    }

    public static Response from(Request request, Response.Builder response, Routes routes) {
        if (!routes.isValidPath(request.uri())) {
            response.status(Status.NOT_FOUND);
            return response.build();
        }

        if (request.method() == Verbs.OPTIONS) {
            response.addHeader("Allow", routes.validAtPath(request));
            response.body(Body.from(""));
            return response.build();
        }

        if (!routes.isValidMethod(request.method(), request.uri())) {
            response.status(Status.METHOD_NOT_ALLOWED);
            response.addHeader("Allow", routes.validAtPath(request));
            return response.build();
        }

        routes.handler(request).handle(request, response);

        response.status(Status.OK);
        response.addHeader("Content-Length", response.bodyLength());

        if (request.method() == Verbs.HEAD) {
            response.body(Body.from(""));
        }

        return response.build();
    }
}
