package com._62bestudy.infra;

import com._62bestudy.domain.Post;
import com._62bestudy.domain.PostRepository;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryPostRepository implements PostRepository {

    private final Map<String, Post> posts = new LinkedHashMap<>();

    @Override
    public Post save(Post post) {
        posts.put(post.getId(), post);
        return post;
    }

    @Override
    public List<Post> findAll() {
        return new ArrayList<>(posts.values());
    }

    @Override
    public Optional<Post> findById(String id) {
        return Optional.ofNullable(posts.get(id));
    }

    @Override
    public void delete(Post post) {
        posts.remove(post.getId());
    }
}
