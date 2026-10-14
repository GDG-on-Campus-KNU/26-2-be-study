package com.example.gdgoc.study.controller;

import com.example.gdgoc.study.domain.InvalidPostException;
import com.example.gdgoc.study.domain.PostNotFoundException;
import com.example.gdgoc.study.dto.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(InvalidPostException.class)
  public ResponseEntity<ErrorResponse> handleInvalidPost(InvalidPostException exception) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(ErrorResponse.of("INVALID_POST", exception.getMessage()));
  }

  @ExceptionHandler(PostNotFoundException.class)
  public ResponseEntity<ErrorResponse> handlePostNotFound(PostNotFoundException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(ErrorResponse.of("POST_NOT_FOUND", exception.getMessage()));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleException(Exception exception) {
    log.error("Internal server error: ", exception);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(ErrorResponse.of("INTERNAL_SERVER_ERROR", "서버 내부 오류가 발생했습니다. 잠시 후 다시 시도해주세요."));
  }
}
