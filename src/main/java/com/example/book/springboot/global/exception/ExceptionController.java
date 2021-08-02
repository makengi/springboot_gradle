package com.example.book.springboot.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class ExceptionController {


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse constraintViolationException(ConstraintViolationException e, HttpServletRequest servletRequest){
//        log.error(e.getMessage());
//
//        final List<ErrorResponse.FieldError> fieldErrors = ExceptionUtil.getFieldErrors(bindingResult);
//        return ExceptionUtil.buildFieldErrors(ErrorCode.INPUT_VALUE_INVALID,fieldErrors,servletRequest);
        return null;
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse methodValidException(MethodArgumentNotValidException e, HttpServletRequest servletRequest){

        log.error(e.getMessage());
        final BindingResult bindingResult = e.getBindingResult();
        final List<ErrorResponse.FieldError> fieldErrors = ExceptionUtil.getFieldErrors(bindingResult);
        return ExceptionUtil.buildFieldErrors(ErrorCode.INPUT_VALUE_INVALID,fieldErrors,servletRequest);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse accountNotFoundExceptionHandler(AccountNotFoundException e){

        final ErrorCode accountNotFoundCode = ErrorCode.ACCOUNT_NOT_FOUND;
        log.error(accountNotFoundCode.getMessage(),e.getId());
        return ExceptionUtil.buildError(accountNotFoundCode);

    }

    @ExceptionHandler(DuplicateEmailException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleConstraintViolationException(DuplicateEmailException e){

        final ErrorCode duplicateEmailCode = ErrorCode.EMAIL_DUPLICATE;
        log.error(duplicateEmailCode.getMessage(),e.getEmail()+e.getField());
        return ExceptionUtil.buildError(duplicateEmailCode);

    }

    @ExceptionHandler(EmailSigninFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse emailInValidException(EmailSigninFailedException e){
        final ErrorCode inValidEmailCode =ErrorCode.EMAIL_INVALID;
        log.error(inValidEmailCode.getMessage(), e.getEmail()+ e.getField());
        return ExceptionUtil.buildError(inValidEmailCode);
    }
}
