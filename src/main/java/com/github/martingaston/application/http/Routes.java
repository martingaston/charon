package com.github.martingaston.application.http;

public class Routes {
    private PathHandler paths;

    public Routes() {
        paths = new PathHandler();
    }

    public void get(URI uri, Handler handler) {
        addRoute(Verbs.GET, uri, handler);
    }

    public void post(URI uri, Handler handler) {
        addRoute(Verbs.POST, uri, handler);
    }

    private void addRoute(Verbs method, URI uri, Handler handler) {
        paths.addPath(uri, new MethodHandler()).addMethod(method, handler);
    }

    public boolean isValid(Verbs method, URI uri) {
        return paths.isValidPath(uri) && paths.get(uri).isValidMethod(method);
    }
}
