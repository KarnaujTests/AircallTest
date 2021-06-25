package com.aircall.es.testscucumber.Utils.Exceptions;

import java.net.http.HttpResponse;

public class RequestException extends Exception {
    final HttpResponse response;

    public RequestException(HttpResponse response, String message) {
        super(message);
        this.response = response;
    }


    public HttpResponse getValue() {
        return response;
    }

}
