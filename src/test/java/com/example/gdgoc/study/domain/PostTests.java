package com.example.gdgoc.study.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class PostTests {

  @Test
  void createPostSuccessfully() {
    Post post = Post.create("제목", "내용");

    assertNull(post.getId());
    assertEquals("제목", post.getTitle());
    assertEquals("내용", post.getContent());
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {" ", "   ", "\t", "\n"})
  void createPostThrowsExceptionWhenTitleIsBlank(String blankTitle) {
    InvalidPostException exception =
        assertThrows(InvalidPostException.class, () -> Post.create(blankTitle, "내용"));

    assertEquals("제목을 입력해주세요.", exception.getMessage());
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {" ", "   ", "\t", "\n"})
  void createPostThrowsExceptionWhenContentIsBlank(String blankContent) {
    InvalidPostException exception =
        assertThrows(InvalidPostException.class, () -> Post.create("제목", blankContent));

    assertEquals("내용을 입력해주세요.", exception.getMessage());
  }

  @Test
  void updatePostSuccessfully() {
    Post post = Post.create("제목", "내용");
    post.update("수정 제목", "수정 내용");

    assertEquals("수정 제목", post.getTitle());
    assertEquals("수정 내용", post.getContent());
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {" ", "   "})
  void updatePostThrowsExceptionWhenTitleIsBlank(String blankTitle) {
    Post post = Post.create("제목", "내용");
    InvalidPostException exception =
        assertThrows(InvalidPostException.class, () -> post.update(blankTitle, "수정 내용"));

    assertEquals("제목을 입력해주세요.", exception.getMessage());
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {" ", "   "})
  void updatePostThrowsExceptionWhenContentIsBlank(String blankContent) {
    Post post = Post.create("제목", "내용");
    InvalidPostException exception =
        assertThrows(InvalidPostException.class, () -> post.update("수정 제목", blankContent));

    assertEquals("내용을 입력해주세요.", exception.getMessage());
  }
}
