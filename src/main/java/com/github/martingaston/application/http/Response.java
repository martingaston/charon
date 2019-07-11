package com.github.martingaston.application.http;

public class Response {
    private Request request;

    public Response(Request request) {
        this.request = request;
    }

    public String respond() {
        String response;

        switch (request.method()) {
            case POST:
                response = "HTTP/1.1 200 OK\r\n\r\n" + request.body().toString();
                break;
            case HEAD:
                response = "HTTP/1.1 200 OK\r\n\r\n";
                break;
            default:
                response = "HTTP/1.1 405 Method Not Allowed\r\n\r\n";
                break;
        }

        return response;
    }
}
