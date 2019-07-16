package com.github.martingaston.application.routes;

import com.github.martingaston.application.http.*;

public class HandleEchoBody implements Handler {
    public HandleEchoBody() {
    }

    @Override
    public Response handle(Request request) {
        Headers headers = new Headers();
        headers.add("Connection", "close");
        headers.add("Content-Length", request.bodyContentLength());
        Body body = Body.from(request.body().toString());
        return new Response(Status.OK, headers, body);
    }
}
