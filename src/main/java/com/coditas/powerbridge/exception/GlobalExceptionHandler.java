package com.coditas.powerbridge.exception;

import com.coditas.powerbridge.dto.response.ErrorResponse;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotAuthenticatedException.class)
    public ResponseEntity<ErrorResponse> userNotAuthenticatedExeptionHandler(UserNotAuthenticatedException exception){
        ErrorResponse errorResponse = createErrorResponse(HttpStatus.FORBIDDEN.value(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }

    @ExceptionHandler(RoleMismatchedException.class)
    public ResponseEntity<ErrorResponse> roleMismatchedExceptionHandler(RoleMismatchedException exception){
        ErrorResponse errorResponse = createErrorResponse(HttpStatus.UNPROCESSABLE_CONTENT.value(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT).body(errorResponse);
    }


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> userNotFoundExceptionHandler(NotFoundException exception){
        ErrorResponse errorResponse = createErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(ResourceAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> resourceAlreadyExistExceptionHandler(ResourceAlreadyExistException exception){
        ErrorResponse errorResponse = createErrorResponse(HttpStatus.CONFLICT.value(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    public ErrorResponse createErrorResponse(Integer status, String message){
        return ErrorResponse.builder()
                .message(message)
                .timestamp(LocalDateTime.now())
                .status(status)
                .build();
    }
}