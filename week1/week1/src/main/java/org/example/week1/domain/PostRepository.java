package org.example.week1.domain;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    Post save(Post post);

    List<Post> findAll();

    Optional<Post> findById(String id);

    void deleteById(String id);
}
