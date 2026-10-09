package com._62bestudy.presentation;

import com._62bestudy.application.PostService;
import com._62bestudy.presentation.dto.PostCreateRequest;
import com._62bestudy.presentation.dto.PostResponse;
import com._62bestudy.presentation.dto.PostUpdateRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponse> create(
            @RequestBody PostCreateRequest request
    ) {
        var post = postService.create(
                request.title(),
                request.body()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(PostResponse.from(post));
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> findAll() {
        var posts = postService.findAll()
                .stream()
                .map(PostResponse::from)
                .toList();

        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> findById(
            @PathVariable String id
    ) {
        var post = postService.findById(id);

        return ResponseEntity.ok(
                PostResponse.from(post)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> update(
            @PathVariable String id,
            @RequestBody PostUpdateRequest request
    ) {
        var post = postService.update(
                id,
                request.title(),
                request.body()
        );

        return ResponseEntity.ok(
                PostResponse.from(post)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable String id
    ) {
        postService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
