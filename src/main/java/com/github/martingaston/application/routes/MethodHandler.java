package com.github.martingaston.application.routes;

import com.github.martingaston.application.http.*;

import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class MethodHandler {
    private ConcurrentHashMap<Verbs, Handler> methods;

    MethodHandler() {
        methods = new ConcurrentHashMap<>();
    }

    void addMethod(Verbs method, Handler handler) {
        methods.putIfAbsent(method, handler);
    }

    Handler get(Verbs method) {
        return methods.getOrDefault(method, request -> {
            var headers = new Headers();
            headers.add("Connection", "close");
            headers.add("Content-Length", 0);
            headers.add("Allow", "HEAD, OPTIONS");
            return new Response(Status.METHOD_NOT_ALLOWED, headers, Body.from(""));
        });
    }


    boolean isValidMethod(Verbs method) {
        return methods.containsKey(method);
    }

    String valid() {
        return methods
                .keySet()
                .stream()
                .map(Verbs::name)
                .sorted()
                .collect(Collectors.joining(", "));
    }
}
