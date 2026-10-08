package com.example.memo;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/memos")
public class MemoController {
    private final MemoService memoService;

    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    @PostMapping
    public ResponseEntity<Memo> create(
            @Valid @RequestBody MemoContent request
    ) {
        Memo memo = memoService.create(request.content());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(memo);
    }

    @GetMapping
    public List<Memo> findAll() {
        return memoService.findAll();
    }

    @GetMapping("/{memo_id}")
    public Memo findById(@PathVariable("memo_id") int id) {
        return memoService.findById(id);
    }

    @PutMapping("/{memo_id}")
    public Memo update(
            @PathVariable("memo_id") int id,
            @Valid @RequestBody MemoContent request
    ) {
        return memoService.update(id, request.content());
    }

    @DeleteMapping("/{memo_id}")
    public MemoDeleteResponse delete(
            @PathVariable("memo_id") int id
    ) {
        memoService.delete(id);
        return new MemoDeleteResponse("삭제 완료", id);
    }
}
