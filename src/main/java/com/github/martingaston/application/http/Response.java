package com.github.martingaston.application.http;

public class Response {
    private Version version;
    private Status status;
    private Headers headers;
    private Body body;

    private Response() {

    }

    public Version version() {
        return this.version;
    }

    public Status status() {
        return this.status;
    }

    public Headers headers() {
        return this.headers;
    }

    public Body body() {
        return this.body;
    }

    public static class Builder {
        private Version version;
        private Status status;
        private Headers headers;
        private Body body;

        public Builder(Status status) {
            this.status = status;
            this.headers = new Headers();
            this.version = Version.V1POINT1;
            this.body = Body.from("");
        }

        public <T> Builder addHeader(String header, T value) {
            headers.add(header, value);
            return this;
        }

        public Builder status(Status status) {
            this.status = status;
            return this;
        }

        public Builder body(Body body) {
            this.body = body;
            return this;
        }

        public int bodyLength() {
            return body.contentLength();
        }

        private void addContentLength(int length) {
            this.headers.add("Content-Length", length);
        }

        public Response build() {
            Response response = new Response();

            response.status = this.status;
            response.version = this.version;
            response.body = this.body;
            addContentLength(this.body.contentLength());
            response.headers = this.headers;

            return response;
        }
    }
}
