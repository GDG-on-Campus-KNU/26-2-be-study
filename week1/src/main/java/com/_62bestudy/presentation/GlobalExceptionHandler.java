package com._62bestudy.presentation;

import com._62bestudy.application.exception.PostNotFoundException;
import com._62bestudy.domain.exception.InvalidPostException;
import com._62bestudy.presentation.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePostNotFound(
            PostNotFoundException exception
    ) {
        var response = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler(InvalidPostException.class)
    public ResponseEntity<ErrorResponse> handleInvalidPost(
            InvalidPostException exception
    ) {
        var response = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }
}
