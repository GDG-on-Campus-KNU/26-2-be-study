package org.example.week1.application;

import lombok.RequiredArgsConstructor;
import org.example.week1.application.exception.PostNotFoundException;
import org.example.week1.domain.Post;
import org.example.week1.domain.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostIdGenerator postIdGenerator;

    public Post post(String title, String content) {
        Post post = new Post(postIdGenerator.generateId(),title,content);
        return postRepository.save(post);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post findById(String id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(
                        "해당 게시글을 찾을 수 없습니다."
                ));
    }

    public Post update(String id, String title, String content) {
        Post post = findById(id);
        post.update(title, content);
        return postRepository.save(post);
    }

    public void delete(String id) {
        postRepository.deleteById(id);
    }
}
