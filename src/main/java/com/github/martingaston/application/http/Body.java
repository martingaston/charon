package com.github.martingaston.application.http;

public class Body {
    private String Body;

    private Body(String Body) {
        this.Body = Body;
    }

    public static Body from(String Body) {
        return new Body(Body);
    }

    @Override
    public String toString() {
        return this.Body;
    }

    @Override
    public boolean equals(Object obj) {
        Body uri = (Body) obj;
        return getClass() == obj.getClass() && this.Body.equals(uri.Body);
    }
}
