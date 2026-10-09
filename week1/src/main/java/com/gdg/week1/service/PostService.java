package com.gdg.week1.service;

import com.gdg.week1.domain.Post;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service

public class PostService {
    private final List<Post> posts = new ArrayList<>();

    public Post create(Post post) {
        var id = UUID.randomUUID().toString();
        post.setId(id);
        posts.add(post);
        return post;
    }

    public List<Post> findAll() {
        return new ArrayList<>(posts);
    }

    public Optional<Post> findById(String id) {
        return posts.stream().filter(post -> post.getId().equals(id)).findFirst();
    }

    public Optional<Post> update(String id, Post request) {
        return findById(id).map(found -> {
            found.setTitle(request.getTitle());
            found.setContent(request.getContent());
            return found;
        });
    }

    public boolean delete(String id) {
        return posts.removeIf(post -> post.getId().equals(id));
    }
}
