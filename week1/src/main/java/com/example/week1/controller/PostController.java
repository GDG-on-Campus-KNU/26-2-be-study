package com.example.week1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.week1.dto.PostCreateRequest;
import com.example.week1.dto.PostUpdateRequest;
import com.example.week1.service.PostService;
import com.example.week1.domain.Post;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;





@RestController
@RequestMapping("/posts")
public class PostController {
    //Spring은 PostService 객체를 알아서 Controller에 넣는다.
    //이를 의존성 주입(DI)이라고 함.
    //new PostService()를 안해도 된다. 
    //즉, 객체 생성은 Spring 프레임워크가 하고, 우리는 컨트롤러 안에 넣어주기만 하는것.
    private final PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    //여기서 @RequestBody가 중요함.
    // /posts 페이지의 HTTP 요청 body에 들어온 JSON 데이터를 java 객체로 반환해서
    //request에 넣는다는 것.
    @PostMapping
    public Post createPost(@RequestBody PostCreateRequest request) {
        

        return postService.createPost(
            request.getTitle(), request.getContent()
        );
    }

    //그냥 /posts로 들어가면 나오는 페이지
    @GetMapping
    public List<Post> getPosts() {
        return postService.getPosts();
    }
    
    // /post/id로 들어가면 나오는 페이지
    @GetMapping("/{id}")
    public Post getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }
    
    @PutMapping("/{id}")
    public Post updatePost(
            @PathVariable Long id,
            @RequestBody PostUpdateRequest request) {

        return postService.updatePost(
            id,
            request.getTitle(),
            request.getContent()
        );
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }

}


