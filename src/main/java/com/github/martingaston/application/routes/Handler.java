package com.github.martingaston.application.routes;

import com.github.martingaston.application.http.Request;
import com.github.martingaston.application.http.Response;

@FunctionalInterface
public interface Handler {
    Response.Builder handle(Request request, Response.Builder response);
}
