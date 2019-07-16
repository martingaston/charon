package com.github.martingaston.application.routes;

import com.github.martingaston.application.http.*;

public class HandleEchoBody implements Handler {
    public HandleEchoBody() {
    }

    @Override
    public Response.Builder handle(Request request, Response.Builder response) {
        return response.body(Body.from(request.body().toString()));
    }
}
