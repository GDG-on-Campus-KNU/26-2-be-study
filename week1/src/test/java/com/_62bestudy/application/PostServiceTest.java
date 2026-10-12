package com._62bestudy.application;

import static org.junit.jupiter.api.Assertions.*;

import com._62bestudy.application.exception.ForbiddenPostAccessException;
import com._62bestudy.application.exception.PostNotFoundException;
import com._62bestudy.domain.User;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Test
    void 게시글을_생성한다() {
        var user = createUser("게시글생성유저");

        var post = postService.create(
                user.getId(),
                "제목",
                "본문"
        );

        assertNotNull(post.getId());
        var id = assertDoesNotThrow(() -> UUID.fromString(post.getId()));
        assertEquals(7, id.version());
        assertNotNull(post.getCreatedAt());
        assertNotNull(post.getUpdatedAt());
        assertEquals("제목", post.getTitle());
        assertEquals("본문", post.getBody());
        assertEquals(user.getId(), post.getAuthor().getId());
        assertEquals(user.getName(), post.getAuthor().getName());
    }

    @Test
    void 게시글을_조회한다() {
        var user = createUser("게시글조회유저");
        var created = postService.create(
                user.getId(),
                "제목",
                "본문"
        );

        var found = postService.findById(created.getId());

        assertEquals(created.getId(), found.getId());
        assertEquals(created.getTitle(), found.getTitle());
        assertEquals(created.getBody(), found.getBody());
        assertEquals(user.getId(), found.getAuthor().getId());
    }

    @Test
    void 작성자의_게시글만_조회한다() {
        var author = createUser("내게시글작성자");
        var otherUser = createUser("내게시글다른유저");
        var firstPost = postService.create(
                author.getId(),
                "제목1",
                "본문1"
        );
        var secondPost = postService.create(
                author.getId(),
                "제목2",
                "본문2"
        );
        postService.create(
                otherUser.getId(),
                "다른제목",
                "다른본문"
        );

        var posts = postService.findAllByAuthorId(author.getId());

        assertEquals(2, posts.size());
        assertTrue(posts.stream().allMatch(post -> post.isAuthor(author.getId())));
        assertTrue(posts.stream().anyMatch(post -> post.getId().equals(firstPost.getId())));
        assertTrue(posts.stream().anyMatch(post -> post.getId().equals(secondPost.getId())));
    }

    @Test
    void 작성자가_게시글을_수정한다() {
        var user = createUser("게시글수정유저");
        var created = postService.create(
                user.getId(),
                "제목",
                "본문"
        );

        var updated = postService.update(
                user.getId(),
                created.getId(),
                "수정제목",
                "수정본문"
        );

        assertEquals("수정제목", updated.getTitle());
        assertEquals("수정본문", updated.getBody());
    }

    @Test
    void 작성자가_아니면_게시글을_수정할_수_없다() {
        var author = createUser("게시글수정작성자");
        var otherUser = createUser("게시글수정다른유저");
        var created = postService.create(
                author.getId(),
                "제목",
                "본문"
        );

        assertThrows(
                ForbiddenPostAccessException.class,
                () -> postService.update(
                        otherUser.getId(),
                        created.getId(),
                        "수정제목",
                        "수정본문"
                )
        );
    }

    @Test
    void 작성자가_아니면_게시글을_삭제할_수_없다() {
        var author = createUser("게시글삭제작성자");
        var otherUser = createUser("게시글삭제다른유저");
        var created = postService.create(
                author.getId(),
                "제목",
                "본문"
        );

        assertThrows(
                ForbiddenPostAccessException.class,
                () -> postService.delete(
                        otherUser.getId(),
                        created.getId()
                )
        );
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
                () -> postService.delete("user-id", id)
        );
    }

    private User createUser(String name) {
        return userService.create(
                name,
                "password"
        );
    }
}
