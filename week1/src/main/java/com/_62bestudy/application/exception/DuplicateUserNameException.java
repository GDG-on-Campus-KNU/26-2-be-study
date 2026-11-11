package com._62bestudy.application.exception;

public class DuplicateUserNameException extends RuntimeException {

    public DuplicateUserNameException(String message) {
        super(message);
    }
}
