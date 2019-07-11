package com.github.martingaston.application.http;

public enum Verbs {
    POST ("POST"),
    INVALID ("Invalid");

    String name;

    Verbs(String name) {
        this.name = name;
    }

    static Verbs from(String name) {
        switch(name) {
            case "POST": return Verbs.POST;
            default: return Verbs.INVALID;
        }
    }
}
