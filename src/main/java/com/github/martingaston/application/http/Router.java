package com.github.martingaston.application.http;

public class Router {
    private Request request;

    public Router(Request request) {
        this.request = request;
    }

    public String respond() {
        String response;

        switch (request.method()) {
            case GET:
                response = "HTTP/1.1 200 OK\r\nConnection: close\r\nContent-Length: 0\r\n\r\n";
                break;
            case POST:
                response = "HTTP/1.1 200 OK\r\nConnection: close\r\nContent-Length: " + request.bodyContentLength() + "\r\n\r\n" + request.body().toString();
                break;
            case HEAD:
                response = "HTTP/1.1 200 OK\r\nConnection: close\r\nContent-Length: " + request.bodyContentLength() + "\r\n\r\n";
                break;
            default:
                response = "HTTP/1.1 405 Method Not Allowed\r\n\r\n";
                break;
        }

        return response;
    }
}
