package org.example.week1.infra;


import org.example.week1.domain.Post;
import org.example.week1.domain.PostRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryPostRepository implements PostRepository {
    private final Map<Long, Post> posts = new HashMap<Long, Post>();

    @Override
    public Post save(Post post) {
        posts.put(post.getId(), post);
        return post;
    }

    @Override
    public List<Post> findAll() {
        return new ArrayList<Post>(posts.values());
    }

    @Override
    public Optional<Post> findById(Long id) {
        return Optional.ofNullable(posts.get(id));
    }

    @Override
    public void deleteById(Long id) {
        posts.remove(id);
    }
}
