package com.techmonks.resilience.webclient;

import org.springframework.http.HttpMethod;

public class GenericRequest<T> {
    private HttpMethod httpMethod;
    private String serviceUrl;
}
