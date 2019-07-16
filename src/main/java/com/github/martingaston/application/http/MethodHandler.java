package com.github.martingaston.application.http;

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
        return methods.get(method);
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
