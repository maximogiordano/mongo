package com.example.mongo.exception;

import com.example.mongo.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@SuppressWarnings({"unused", "java:S6212"})
public class GlobalExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> handleEntityNotFoundException(EntityNotFoundException e) {
        return handleException(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityOperationException.class)
    public ResponseEntity<ErrorDto> handleEntityOperationException(EntityOperationException e) {
        return handleException(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleException(Exception e) {
        return handleException(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorDto> handleException(String message, HttpStatus status) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(message);
        return new ResponseEntity<>(errorDto, status);
    }
}
