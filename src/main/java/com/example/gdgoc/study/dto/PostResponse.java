package com.example.gdgoc.study.dto;

import com.example.gdgoc.study.domain.Post;

public record PostResponse(Long id, String title, String content) {
  public static PostResponse from(Post post) {
    return new PostResponse(post.getId(), post.getTitle(), post.getContent());
  }
}
