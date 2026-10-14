package com.example.gdgoc.study.domain;

public class PostNotFoundException extends RuntimeException {

  private final Long postId;

  public PostNotFoundException(Long id) {
    super("존재하지 않는 게시글입니다.");
    this.postId = id;
  }

  public Long getPostId() {
    return postId;
  }
}
