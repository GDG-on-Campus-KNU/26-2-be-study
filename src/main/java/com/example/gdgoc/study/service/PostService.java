package com.example.gdgoc.study.service;

import com.example.gdgoc.study.domain.Post;
import com.example.gdgoc.study.domain.PostNotFoundException;
import com.example.gdgoc.study.repository.PostRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PostService {

  private final PostRepository postRepository;

  public PostService(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  public Post createPost(String title, String content) {
    return postRepository.save(Post.create(title, content));
  }

  public List<Post> getPosts() {
    return postRepository.findAll();
  }

  public Post getPost(Long id) {
    return postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
  }

  @Transactional
  public Post updatePost(Long id, String title, String content) {
    Post existingPost = getPost(id);
    existingPost.update(title, content);
    return existingPost;
  }

  public void deletePost(Long id) {
    postRepository.delete(getPost(id));
  }
}
