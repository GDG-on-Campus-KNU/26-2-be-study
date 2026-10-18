package com.example.memo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MemoNotFoundException extends RuntimeException {

    public MemoNotFoundException(long id) {
        super("Memo not found: " + id);
    }
}
