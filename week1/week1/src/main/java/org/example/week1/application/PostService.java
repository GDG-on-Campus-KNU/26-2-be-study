package org.example.week1.application;

import lombok.RequiredArgsConstructor;
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

    public Post findById(Long id) {
        return postRepository.findById(id)
                .orElseThrow();
    }

    public Post update(Long id, String title, String content) {   // ")" 빠져 있었고, 파라미터도 변경
        Post post = findById(id);
        post.update(title, content);
        return postRepository.save(post);
    }

    public void delete(Long id) {
        postRepository.deleteById(id);   // void 메서드에서 return 제거
    }
}
