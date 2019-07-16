package com.github.martingaston.application.routes;

import com.github.martingaston.application.http.*;

public class HandleEchoBody implements Handler {
    public HandleEchoBody() {
    }

    @Override
    public Response handle(Request request) {
        return new Response.Builder(Status.OK)
                .addHeader("Connection", "Close")
                .body(Body.from(request.body().toString()))
                .build();
    }
}
