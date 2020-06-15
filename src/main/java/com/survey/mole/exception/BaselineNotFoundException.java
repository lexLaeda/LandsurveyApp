package com.survey.mole.exception;

public class BaselineNotFoundException extends RuntimeException {

    public BaselineNotFoundException() {
    }

    public BaselineNotFoundException(String message) {
        super(message);
    }

    public BaselineNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
