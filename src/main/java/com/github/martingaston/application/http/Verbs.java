package com.github.martingaston.application.http;

public enum Verbs {
    GET ("GET"),
    POST ("POST"),
    HEAD ("HEAD"),
    INVALID ("Invalid");

    String name;

    Verbs(String name) {
        this.name = name;
    }

    static Verbs from(String name) {
        switch(name) {
            case "GET": return Verbs.GET;
            case "POST": return Verbs.POST;
            case "HEAD": return Verbs.HEAD;
            default: return Verbs.INVALID;
        }
    }
}
