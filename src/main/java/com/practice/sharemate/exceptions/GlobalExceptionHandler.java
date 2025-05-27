package com.practice.sharemate.exceptions;

import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseStatusException handleValidationException(MethodArgumentNotValidException ex) {
        log.error("ValidationException: " + ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return new ResponseStatusException(HttpStatusCode.valueOf(400), ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseStatusException handleRuntimeException(RuntimeException ex) {
        log.error("RuntimeException: " + ex.getMessage());
        return new ResponseStatusException(HttpStatusCode.valueOf(500), ex.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseStatusException handleNullPointerException(NullPointerException ex) {
        log.error("NullPointerException" + ex.getMessage());
        return new ResponseStatusException(HttpStatusCode.valueOf(500), ex.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseStatusException handleNotFoundException(MethodArgumentNotValidException ex) {
        log.error("NotFoundException" + ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return new ResponseStatusException(HttpStatusCode.valueOf(404), ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

}
