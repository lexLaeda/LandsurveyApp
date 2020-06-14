package com.survey.mole.exception;

public class LevelReferenceException extends RuntimeException {
    public LevelReferenceException() {
    }

    public LevelReferenceException(String message) {
        super(message);
    }

    public LevelReferenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
