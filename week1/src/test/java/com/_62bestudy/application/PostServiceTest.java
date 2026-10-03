package com._62bestudy.application;

import static org.junit.jupiter.api.Assertions.*;

import com._62bestudy.application.exception.PostNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Test
    void 게시글을_생성한다() {
        var post = postService.create(
                "제목",
                "본문"
        );

        assertEquals("제목", post.getTitle());
        assertEquals("본문", post.getBody());
    }

    @Test
    void 게시글을_조회한다() {
        var created = postService.create(
                "제목",
                "본문"
        );

        var found = postService.findById(created.getId());

        assertEquals(created.getId(), found.getId());
        assertEquals(created.getTitle(), found.getTitle());
        assertEquals(created.getBody(), found.getBody());
    }

    @Test
    void 존재하지_않는_게시글을_조회하면_예외가_발생한다() {
        var id = "not-exist-id";

        assertThrows(
                PostNotFoundException.class,
                () -> postService.findById(id)
        );
    }

    @Test
    void 존재하지_않는_게시글을_삭제하면_예외가_발생한다() {
        var id = "not-exist-id";

        assertThrows(
                PostNotFoundException.class,
                () -> postService.delete(id)
        );
    }
}
