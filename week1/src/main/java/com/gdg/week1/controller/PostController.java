package com.gdg.week1.controller;

import com.gdg.week1.domain.Post;
import com.gdg.week1.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")

public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Post> create(@RequestBody Post post) {
        var result = postService.create(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }


}
