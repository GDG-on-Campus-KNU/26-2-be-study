package com._62bestudy.domain;

import static org.junit.jupiter.api.Assertions.*;

import com._62bestudy.domain.exception.InvalidPostException;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class PostTest {

    @Test
    void 게시글을_생성한다() {
        var id = "post-id";
        var title = "제목";
        var body = "본문";
        var createdAt = LocalDateTime.of(2026, 10, 3, 12, 0);

        var post = new Post(id, title, body, createdAt);

        assertEquals(id, post.getId());
        assertEquals(title, post.getTitle());
        assertEquals(body, post.getBody());
        assertEquals(createdAt, post.getCreatedAt());
    }

    @Test
    void 게시글을_수정한다() {
        var post = createPost();

        post.update("수정된 제목", "수정된 본문");

        assertEquals("수정된 제목", post.getTitle());
        assertEquals("수정된 본문", post.getBody());
    }

    @Test
    void 제목이_100자를_초과하면_예외가_발생한다() {
        var title = "a".repeat(101);

        assertThrows(
                InvalidPostException.class,
                () -> new Post(
                        "post-id",
                        title,
                        "본문",
                        LocalDateTime.now()
                )
        );
    }

    @Test
    void 본문이_5000자를_초과하면_예외가_발생한다() {
        var body = "a".repeat(5001);

        assertThrows(
                InvalidPostException.class,
                () -> new Post(
                        "post-id",
                        "제목",
                        body,
                        LocalDateTime.now()
                )
        );
    }

    private Post createPost() {
        return new Post(
                "post-id",
                "제목",
                "본문",
                LocalDateTime.of(2026, 10, 3, 12, 0)
        );
    }
}
