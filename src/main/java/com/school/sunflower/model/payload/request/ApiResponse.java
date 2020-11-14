package com.school.sunflower.model.payload.request;

import lombok.Builder;
import lombok.Value;
import org.springframework.http.HttpStatus;

@Value
@Builder
public class ApiResponse<T> {

    @Builder.Default
    private Boolean success = true;
    private ErrorPayload errors;
    @Builder.Default
    private Integer code = HttpStatus.OK.value();
    private T payload;


    public static <T> ApiResponse<T> ok(T payload) {
        return status(HttpStatus.OK, payload);
    }

    public static <T> ApiResponse<T> created(T payload) {
        return status(HttpStatus.CREATED, payload);
    }

    public static <T> ApiResponse<T> accepted(T payload) {
        return status(HttpStatus.ACCEPTED, payload);
    }

    private static <T> ApiResponse<T> status(HttpStatus status, T payload) {
        return new ApiResponse(true, null, status.value(), payload);
    }

}
