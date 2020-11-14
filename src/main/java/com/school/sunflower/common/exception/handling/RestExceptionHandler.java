package com.school.sunflower.common.exception.handling;

import com.school.sunflower.common.exception.BusinessException;
import com.school.sunflower.common.exception.ValidationException;
import com.school.sunflower.model.payload.request.ApiResponse;
import com.school.sunflower.model.payload.request.ErrorPayload;
import com.school.sunflower.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        log.error("handleMethodArgumentNotValid :: Error during validating user request:", ex);

        String errorMessage = Constants.ErrorKeys.VALIDATION_ERROR;
        String arErrorMessage = Constants.ErrorKeys.VALIDATION_ERROR_AR;

        Map<String, Object> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            try {

                errorMessage = messageSource.getMessage(error.getDefaultMessage(), null, new Locale("en"));
                arErrorMessage = messageSource.getMessage(error.getDefaultMessage(), null, new Locale("ar"));

            } catch (NoSuchMessageException messageEx) {
                errorMessage = error.getDefaultMessage();
                arErrorMessage = error.getDefaultMessage();
            }

            errors.put(error.getField() + "_EN", errorMessage);
            errors.put(error.getField() + "_AR", arErrorMessage);
        }

        return new ResponseEntity<>(
                ApiResponse.builder()
                        .success(Boolean.FALSE)
                        .errors(ErrorPayload.builder()
                                .enMessage(errorMessage).arMessage(arErrorMessage)
                                .type(ValidationException.class.getSimpleName())
                                .details(errors)
                                .build())
                        .code(HttpStatus.BAD_REQUEST.value()).build()
                , HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("handleTypeMismatch :: Error during validating user request:", ex);

        Map<String, Object> errors = new HashMap<>();
        errors.put(ex.getPropertyName(), ex.getValue());


        return new ResponseEntity<>(
                ApiResponse.builder()
                        .success(Boolean.FALSE)
                        .errors(ErrorPayload.builder()
                                .type(ValidationException.class.getSimpleName())
                                .details(errors)
                                .build())
                        .code(HttpStatus.BAD_REQUEST.value()).build()
                , HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("handleBindException :: Error during validating user request:", ex);

        String errorMessage;
        String arErrorMessage;

        Map<String, Object> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            try {

                errorMessage = messageSource.getMessage(error.getDefaultMessage(), null, new Locale("en"));
                arErrorMessage = messageSource.getMessage(error.getDefaultMessage(), null, new Locale("ar"));

            } catch (NoSuchMessageException messageEx) {
                errorMessage = error.getDefaultMessage();
                arErrorMessage = error.getDefaultMessage();
            }

            errors.put(error.getField() + "_EN", errorMessage);
            errors.put(error.getField() + "_AR", arErrorMessage);
        }

        return new ResponseEntity<>(
                ApiResponse.builder()
                        .success(Boolean.FALSE)
                        .errors(ErrorPayload.builder()
                                .type(ValidationException.class.getSimpleName())
                                .details(errors)
                                .build())
                        .code(HttpStatus.BAD_REQUEST.value()).build()
                , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse> handleBusinessException(BusinessException ex,
                                                               WebRequest request) {
        log.error("Business error: ", ex);
        Object[] args = null;
        if (Objects.nonNull(ex.getArgs())) {
            args = ex.getArgs().toArray();
        }
        String errorMessage;
        String arErrorMessage;
        try {
            errorMessage = messageSource.getMessage(ex.getMessage(), args, new Locale("en"));
            arErrorMessage = messageSource.getMessage(ex.getMessage(), args, new Locale("ar"));
        } catch (NoSuchMessageException messageEx) {
            errorMessage = ex.getMessage();
            arErrorMessage = ex.getMessage();
        }
        Map<String, Object> errors = new HashMap<>();
        if (ex.getDetails() != null) {
            for (Map.Entry<String, String> error : ex.getDetails().entrySet()) {
                try {
                    errorMessage = messageSource.getMessage(error.getValue(), null, new Locale("en"));
                    arErrorMessage = messageSource.getMessage(error.getValue(), null, new Locale("ar"));
                } catch (NoSuchMessageException messageEx) {
                }
                errors.put(error.getKey() + "_EN", errorMessage);
                errors.put(error.getKey() + "_AR", arErrorMessage);
            }
        }
        return new ResponseEntity<>(
                ApiResponse.builder()
                        .success(Boolean.FALSE)
                        .errors(ErrorPayload.builder().enMessage(errorMessage).arMessage(arErrorMessage)
                                .type(ex.getClass().getSimpleName()).details(errors).build())
                        .code(ex.getStatus().value()).build()
                , ex.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        log.error("Unexpected error: ", ex);
        String errorMessage;
        String arErrorMessage;
        try {
            errorMessage = messageSource
                    .getMessage(ex.getClass().getSimpleName(), null, new Locale("en"));
            arErrorMessage = messageSource
                    .getMessage(ex.getClass().getSimpleName(), null, new Locale("ar"));
        } catch (NoSuchMessageException messageEx) {
            errorMessage = ex.getMessage();
            arErrorMessage = ex.getMessage();
        }
        return new ResponseEntity<>(
                ApiResponse.builder()
                        .success(Boolean.FALSE)
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .errors(ErrorPayload.builder().enMessage(errorMessage).arMessage(arErrorMessage)
                                .type(ex.getClass().getSimpleName()).build())
                        .build()
                , HttpStatus.INTERNAL_SERVER_ERROR);
    }


}