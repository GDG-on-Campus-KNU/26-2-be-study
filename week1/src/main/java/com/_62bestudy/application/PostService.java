package com._62bestudy.application;

import com._62bestudy.application.exception.PostNotFoundException;
import com._62bestudy.domain.Post;
import com._62bestudy.domain.PostRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostIdGenerator postIdGenerator;

    public Post create(String title, String body) {
        var id = postIdGenerator.generate();

        var post = new Post(
                id,
                title,
                body,
                LocalDateTime.now()
        );

        return postRepository.save(post);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post findById(String id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(
                        "게시글을 찾을 수 없습니다."
                ));
    }

    public Post update(String id, String title, String body) {
        var post = findById(id);

        post.update(title, body);

        return postRepository.save(post);
    }

    public void delete(String id) {
        var post = findById(id);

        postRepository.delete(post);
    }
}
