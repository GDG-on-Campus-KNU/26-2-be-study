package com._62bestudy.infra;

import com._62bestudy.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaPostRepository extends JpaRepository<Post, String> {
}
