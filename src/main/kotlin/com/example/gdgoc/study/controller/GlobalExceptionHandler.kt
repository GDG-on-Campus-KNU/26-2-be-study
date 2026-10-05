package com.example.gdgoc.study.controller

import com.example.gdgoc.study.domain.PostNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(PostNotFoundException::class)
    fun handlePostNotFound(exception: PostNotFoundException): ResponseEntity<String> = ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.message)
}
