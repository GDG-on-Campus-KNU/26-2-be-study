package com.example.week1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.week1.domain.Post;
import com.example.week1.dto.PostCreateRequest;
import com.example.week1.dto.PostUpdateRequest;
import com.example.week1.service.PostService;
import com.example.week1.dto.PostResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity; //ResponseEntity : HTTP 응답 본문, 상태 코드, 헤더를 함께 제어할 수 있도록 하는 Spring 클래스 -> 원하는 상태 코드 명시 가능

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
    // Spring은 PostService 객체를 알아서 Controller에 넣는다.
    // 이를 의존성 주입(DI)이라고 함.
    // new PostService()를 안해도 된다.
    // 즉, 객체 생성은 Spring 프레임워크가 하고, 우리는 컨트롤러 안에 넣어주기만 하는것.
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 여기서 @RequestBody가 중요함.
    // /posts 페이지의 HTTP 요청 body에 들어온 JSON 데이터를 java 객체로 반환해서
    // request에 넣는다는 것.
    @PostMapping
    public ResponseEntity<PostResponse> createPost(
            @RequestBody PostCreateRequest request) {

        PostResponse response = postService.createPost(
                request.getTitle(),
                request.getContent());
        // stream API
        return ResponseEntity
                .status(HttpStatus.CREATED) // 상태코드 201 Created
                .body(response); // 실제 응답 본문 설정
    }

    // 그냥 /posts로 들어가면 나오는 페이지
    @GetMapping
    public List<PostResponse> getPosts() {
        return postService.getPosts();
    }

    // /post/id로 들어가면 나오는 페이지
    // Entity를 그대로 반환하면 내부필드가 모두 JSON으로 변환되어 표시하고 싶지 않은 데이터까지 포함될 수 있음! DTO를 쓰자
    @GetMapping("/{id}")
    public PostResponse getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @PutMapping("/{id}")
    public PostResponse updatePost(
            @PathVariable Long id,
            @RequestBody PostUpdateRequest request) {

        return postService.updatePost(
                id,
                request.getTitle(),
                request.getContent());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) { // 삭제 성공 후 응답 본문이 없으니까 Void
        postService.deletePost(id);

        return ResponseEntity.noContent().build(); // HTTP 204 No Content응답을 반환
    }

}