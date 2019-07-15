package com.github.martingaston.application.http;

interface Handler {
    Response handle(Request request);
}
