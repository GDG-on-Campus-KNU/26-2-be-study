package com.example.gdgoc.study.controller;

import com.example.gdgoc.study.domain.Post;
import com.example.gdgoc.study.dto.CreatePostRequest;
import com.example.gdgoc.study.dto.PostResponse;
import com.example.gdgoc.study.dto.UpdatePostRequest;
import com.example.gdgoc.study.service.PostService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class PostController {

  private final PostService postService;

  public PostController(PostService postService) {
    this.postService = postService;
  }

  @PostMapping
  public ResponseEntity<PostResponse> createPost(@Valid @RequestBody CreatePostRequest request) {
    Post post = postService.createPost(request.title(), request.content());
    return ResponseEntity.created(URI.create("/posts/" + post.getId()))
        .body(PostResponse.from(post));
  }

  @GetMapping
  public ResponseEntity<List<PostResponse>> getPosts() {
    return ResponseEntity.ok(postService.getPosts().stream().map(PostResponse::from).toList());
  }

  @GetMapping("/{id}")
  public ResponseEntity<PostResponse> getPost(@PathVariable Long id) {
    return ResponseEntity.ok(PostResponse.from(postService.getPost(id)));
  }

  @PutMapping("/{id}")
  public ResponseEntity<PostResponse> updatePost(
      @PathVariable Long id, @Valid @RequestBody UpdatePostRequest request) {
    return ResponseEntity.ok(
        PostResponse.from(postService.updatePost(id, request.title(), request.content())));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePost(@PathVariable Long id) {
    postService.deletePost(id);
    return ResponseEntity.noContent().build();
  }
}
