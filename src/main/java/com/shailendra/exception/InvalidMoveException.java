package com.shailendra.exception;

public class InvalidMoveException extends RuntimeException {

    public InvalidMoveException() {

    }

    public InvalidMoveException(String message) {
        super(message);
    }
}
