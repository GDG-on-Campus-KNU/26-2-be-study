package org.example.week1.domain.post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    // 게시물 저장
    Post save(Post post);

    // 게시물 모두 가져오기
    List<Post> findAll();

    // 게시물 단건 가져오기
    Optional<Post> findById(Long id);

    // 게시물 삭제하기
    void deleteById(Long id);
}
