package com.github.martingaston.application.routes;

import com.github.martingaston.application.http.*;

public class Routes {
    private PathHandler paths;

    public Routes() {
        paths = new PathHandler();
    }

    public Response handle(Request request, Response.Builder response) {
        if(!paths.isValidPath(request.uri())) {
            response.status(Status.NOT_FOUND);
            return response.build();
        }

        if(request.method() == Verbs.OPTIONS) {
            response.addHeader("Allow", paths.get(request.uri()).valid());
            response.body(Body.from(""));
            return response.build();
        }

        if(!paths.get(request.uri()).isValidMethod(request.method())) {
           response.status(Status.METHOD_NOT_ALLOWED);
           response.addHeader("Allow", paths.get(request.uri()).valid());
           return response.build();
        }

        handler(request).handle(request, response);

        response.status(Status.OK);
        response.addHeader("Content-Length", response.bodyLength());

        if(request.method() == Verbs.HEAD) {
            response.body(Body.from(""));
        }

        return response.build();
    }

    private Handler handler(Request request) {
        return paths.get(request.uri()).get(request.method());
    }

    public void get(URI uri, Handler handler) {
        addRoute(Verbs.GET, uri, handler);
        addRoute(Verbs.HEAD, uri, handler);
        addRoute(Verbs.OPTIONS, uri, handler);
    }

    public void post(URI uri, Handler handler) {
        addRoute(Verbs.POST, uri, handler);
        addRoute(Verbs.OPTIONS, uri, handler);
    }

    public void put(URI uri, Handler handler) {
        addRoute(Verbs.PUT, uri, handler);
        addRoute(Verbs.OPTIONS, uri, handler);
    }

    public void head(URI uri, Handler handler) {
        addRoute(Verbs.HEAD, uri, handler);
    }

    private void addRoute(Verbs method, URI uri, Handler handler) {
        paths.addPath(uri, new MethodHandler()).addMethod(method, handler);
    }

    public boolean isValid(Verbs method, URI uri) {
        return paths.isValidPath(uri) && paths.get(uri).isValidMethod(method);
    }
}
