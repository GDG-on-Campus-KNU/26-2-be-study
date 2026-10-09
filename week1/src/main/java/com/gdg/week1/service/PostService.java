package com.gdg.week1.service;

import com.gdg.week1.domain.Post;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
}
