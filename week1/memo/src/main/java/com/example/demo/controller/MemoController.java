package com.example.demo.controller;

import com.example.demo.dto.MemoDto;
import com.example.demo.entity.Memo;
import com.example.demo.service.MemoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/memos")
public class MemoController {
    private final MemoService memoService;

    public MemoController(MemoService memoService){
        this.memoService = memoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Memo create(@Valid @RequestBody MemoDto dto){
        return memoService.create(dto);
    }

    @GetMapping
    public List<Memo> findAll(){
        return memoService.findAll();
    }
}
