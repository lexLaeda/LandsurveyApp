package com.survey.mole.exception;

public class CodeNotFoundException extends RuntimeException {
    public CodeNotFoundException() {
    }

    public CodeNotFoundException(String message) {
        super(message);
    }

    public CodeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
