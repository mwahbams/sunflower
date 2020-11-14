package com.school.sunflower.common.exception;

import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

public class ValidationException extends BusinessException {


    public ValidationException(HttpStatus status) {
        super(status);
    }

    public ValidationException(String message) {
        super(message, HttpStatus.NOT_ACCEPTABLE);
    }

    public ValidationException(String message, List<String> args) {
        super(message, HttpStatus.NOT_ACCEPTABLE, args);
    }

    public ValidationException(String message, HttpStatus status) {
        super(message, status);
    }

    public ValidationException(String message, HttpStatus status, Map<String, String> details) {
        super(message, status, details);
    }

    public ValidationException(String message, Throwable cause, HttpStatus status) {
        super(message, cause, status);
    }
}
