package com._62bestudy.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, String> {

    List<Post> findAllByAuthorId(String authorId);
}
