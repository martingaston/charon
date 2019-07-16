package com.github.martingaston.application.routes;

import com.github.martingaston.application.http.Request;
import com.github.martingaston.application.http.Response;

public interface Handler {
    Response handle(Request request);
}
