package com._62bestudy.application.exception;

public class ForbiddenPostAccessException extends RuntimeException {

    public ForbiddenPostAccessException(String message) {
        super(message);
    }
}
