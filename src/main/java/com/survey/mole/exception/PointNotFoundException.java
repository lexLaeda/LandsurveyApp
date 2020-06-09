package com.survey.mole.exception;

public class PointNotFoundException extends RuntimeException {

    public PointNotFoundException() {
    }

    public PointNotFoundException(String message) {
        super(message);
    }

    public PointNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
