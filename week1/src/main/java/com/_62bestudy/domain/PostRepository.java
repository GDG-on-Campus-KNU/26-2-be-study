package com._62bestudy.domain;

import java.util.List;
import java.util.Optional;

public interface PostRepository {

    Post save(Post post);

    Optional<Post> findById(String id);

    List<Post> findAll();

    void delete(Post post);
}
