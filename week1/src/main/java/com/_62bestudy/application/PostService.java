package com._62bestudy.application;

import com._62bestudy.application.exception.ForbiddenPostAccessException;
import com._62bestudy.application.exception.PostNotFoundException;
import com._62bestudy.domain.Post;
import com._62bestudy.domain.PostRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;
    private final IdGenerator idGenerator;

    public Post create(String userId, String title, String body) {
        var author = userService.findById(userId);
        var post = Post.create(
                idGenerator.generate(),
                title,
                body,
                author
        );

        return postRepository.save(post);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public List<Post> findAllByAuthorId(String authorId) {
        return postRepository.findAllByAuthorId(authorId);
    }

    public Post findById(String id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(
                        "게시글을 찾을 수 없습니다."
                ));
    }

    public Post update(String userId, String id, String title, String body) {
        var post = findById(id);
        validateAuthor(userId, post);

        post.update(title, body);

        return postRepository.save(post);
    }

    public void delete(String userId, String id) {
        var post = findById(id);
        validateAuthor(userId, post);

        postRepository.delete(post);
    }

    private void validateAuthor(String userId, Post post) {
        if (!post.isAuthor(userId)) {
            throw new ForbiddenPostAccessException("게시글 작성자만 접근할 수 있습니다.");
        }
    }
}
