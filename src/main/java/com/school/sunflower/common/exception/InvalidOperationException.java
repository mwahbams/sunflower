package com.school.sunflower.common.exception;

import org.springframework.http.HttpStatus;

public class InvalidOperationException extends BusinessException {

    public InvalidOperationException() {
        super(HttpStatus.BAD_REQUEST);
    }

    public InvalidOperationException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

    public InvalidOperationException(String message, Throwable cause) {
        super(message, cause, HttpStatus.BAD_REQUEST);
    }
}
