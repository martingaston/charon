package com.github.martingaston.application.http;

import java.util.concurrent.ConcurrentHashMap;

class PathHandler {
    private ConcurrentHashMap<URI, MethodHandler> paths;

    PathHandler() {
        paths = new ConcurrentHashMap<>();
    }

    public void add(URI uri, MethodHandler methods) {
       paths.putIfAbsent(uri, methods);
    }

    public boolean isValidPath(URI uri) {
        return paths.containsKey(uri);
    }

    public MethodHandler get(URI uri) {
        return paths.get(uri);
    }
}