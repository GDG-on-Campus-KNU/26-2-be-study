package org.example.week1.domain.post;

import lombok.RequiredArgsConstructor;
import org.example.week1.domain.post.dto.PostRequest;
import org.example.week1.domain.post.dto.PostResponse;
import org.example.week1.domain.post.dto.PostUpdateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponse> post(@RequestBody PostRequest request) {
        Post post = postService.post(request.getTitle(), request.getContent());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(PostResponse.from(post));
    }

    @GetMapping
    public ResponseEntity<List<Post>> getPosts() {
        List<Post> posts = postService.findAll();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable Long id) {
        return ResponseEntity.ok(postService.findById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PostResponse> patch(@PathVariable Long id,
                                              @RequestBody PostUpdateRequest request) {
        Post post = postService.update(id, request.getTitle(), request.getContent());
        return ResponseEntity.ok(PostResponse.from(post));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
