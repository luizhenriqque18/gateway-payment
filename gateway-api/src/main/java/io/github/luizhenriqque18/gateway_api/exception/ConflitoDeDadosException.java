package io.github.luizhenriqque18.gateway_api.exception;

public class ConflitoDeDadosException extends RuntimeException {
    public ConflitoDeDadosException(String message) {
        super(message);
    }

    public ConflitoDeDadosException(String message, Throwable cause) {
        super(message, cause);
    }
}