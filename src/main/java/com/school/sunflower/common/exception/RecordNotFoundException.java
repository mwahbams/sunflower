package com.school.sunflower.common.exception;

import org.springframework.http.HttpStatus;

public class RecordNotFoundException extends BusinessException {

    public RecordNotFoundException() {
        this("RECORD_NOT_FOUND");
    }

    public RecordNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }

    public RecordNotFoundException(String message, Throwable cause) {
        super(message, cause, HttpStatus.NOT_FOUND);
    }
}
