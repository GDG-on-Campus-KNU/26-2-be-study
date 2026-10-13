package org.example.week1.presentation;

import org.example.week1.application.exception.PostNotFoundException;
import org.example.week1.presentation.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePostNotFoundException(PostNotFoundException ex){
        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }
}
