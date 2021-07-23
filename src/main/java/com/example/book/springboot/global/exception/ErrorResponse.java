package com.example.book.springboot.global.exception;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.FieldError;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

    private String code;
    private String message;
    private int status;
    private List<FieldError> errors;
    private String requestURI;
    private String date;

    @Builder
    public ErrorResponse(String code, String message, int status, List<FieldError> errors,String requestURI,String date) {
        this.code = code;
        this.message = message;
        this.status = status;
        this.errors = errors;
        this.requestURI = requestURI;
        this.date = date;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class FieldError{
        private String field;
        private String value;
        private String reason;

        @Builder
        public FieldError(String field, String value, String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }
    }

}
