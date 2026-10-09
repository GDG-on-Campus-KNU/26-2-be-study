package com._62bestudy.infra;

import com._62bestudy.domain.Post;
import com._62bestudy.domain.PostRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JpaPostRepository implements PostRepository {

    private final SpringDataJpaPostRepository repository;

    @Override
    public Post save(Post post) {
        return repository.save(post);
    }

    @Override
    public Optional<Post> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public List<Post> findAll() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "createdAt"));
    }

    @Override
    public void delete(Post post) {
        repository.delete(post);
    }
}
