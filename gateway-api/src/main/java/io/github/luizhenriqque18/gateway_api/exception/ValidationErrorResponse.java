package io.github.luizhenriqque18.gateway_api.exception;

import java.util.Map;

import lombok.Getter;


@Getter
public class ValidationErrorResponse {
    private String message;
    private Map<String, String> errors;

    public ValidationErrorResponse(String message, Map<String, String> errors) {
        this.message = message;
        this.errors = errors;
    }
}