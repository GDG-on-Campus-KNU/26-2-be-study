package com._62bestudy.domain;

import static org.junit.jupiter.api.Assertions.*;

import com._62bestudy.domain.exception.InvalidPostException;
import org.junit.jupiter.api.Test;

class PostTest {

    @Test
    void 게시글을_생성한다() {
        var id = "post-id";
        var title = "제목";
        var body = "본문";
        var author = createUser();

        var post = Post.create(id, title, body, author);

        assertEquals(id, post.getId());
        assertNull(post.getCreatedAt());
        assertNull(post.getUpdatedAt());
        assertEquals(title, post.getTitle());
        assertEquals(body, post.getBody());
        assertEquals(author, post.getAuthor());
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
                () -> Post.create(
                        "post-id",
                        title,
                        "본문",
                        createUser()
                )
        );
    }

    @Test
    void 본문이_5000자를_초과하면_예외가_발생한다() {
        var body = "a".repeat(5001);

        assertThrows(
                InvalidPostException.class,
                () -> Post.create(
                        "post-id",
                        "제목",
                        body,
                        createUser()
                )
        );
    }

    private Post createPost() {
        return Post.create(
                "post-id",
                "제목",
                "본문",
                createUser()
        );
    }

    private User createUser() {
        return User.create(
                "user-id",
                "유저",
                "password"
        );
    }
}
