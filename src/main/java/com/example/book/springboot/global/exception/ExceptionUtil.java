package com.example.book.springboot.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ExceptionUtil {

    public static String dateFormat;

    @Value("${date.format}")
    public void setDateFormat(String df){
        dateFormat = df;
    }

    public static List<ErrorResponse.FieldError> getFieldErrors(BindingResult bindingResult){
        final List<FieldError> errors = bindingResult.getFieldErrors();
        return errors.parallelStream()
                .map(error->ErrorResponse.FieldError.builder()
                    .reason(error.getDefaultMessage())
                    .field(error.getField())
                    .value((String)error.getRejectedValue())
                    .build()
                ).collect(Collectors.toList());
    }

    public static ErrorResponse buildFieldErrors(ErrorCode errorCode, List<ErrorResponse.FieldError> errors, HttpServletRequest servletRequest) {

        log.info("@ formatString: {}",dateFormat);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        log.info("@ requestUri: {}",servletRequest.getRequestURI());
        log.info("@ timestamp: {}", Calendar.getInstance().getTime());
        log.info("@ date: {}",simpleDateFormat.format(Calendar.getInstance().getTime()));

        return ErrorResponse.builder()
                .code(errorCode.getCode())
                .status(errorCode.getStatus())
                .message(errorCode.getMessage())
                .errors(errors)
                .requestURI(servletRequest.getRequestURI())
                .date(simpleDateFormat.format(Calendar.getInstance().getTime()))
                .build();
    }

    public static ErrorResponse buildError(ErrorCode errorCode) {
        return ErrorResponse.builder()
                .code(errorCode.getCode())
                .status(errorCode.getStatus())
                .message(errorCode.getMessage())
                .build();
    }



}
