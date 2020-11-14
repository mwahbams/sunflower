package com.school.sunflower.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

@Getter
public abstract class BusinessException extends RuntimeException {

    private String message;
    private Throwable cause;
    private HttpStatus status;
    private Map<String, String> details;
    private List<String> args;

    public BusinessException(HttpStatus status) {
        this.status = status;
    }

    public BusinessException(String message, HttpStatus status, List<String> args) {
        super(message);
        this.message = message;
        this.args = args;
        this.status = status;
    }

    public BusinessException(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }

    public BusinessException(String message, HttpStatus status, Map<String, String> details) {
        super(message);
        this.message = message;
        this.status = status;
        this.details = details;
    }

    public BusinessException(String message, Throwable cause, HttpStatus status) {
        super(message, cause);
        this.status = status;
    }

}
