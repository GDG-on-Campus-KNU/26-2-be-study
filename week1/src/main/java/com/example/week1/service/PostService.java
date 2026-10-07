package com.example.week1.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service; //@Service 어노테이션을 쓰기 위한 import문

import com.example.week1.domain.Post; //domain에 있는 Post.java파일을 가져오기 위함

@Service 
public class PostService {
    //빠른 접근을 위해 HashMap을 데이터베이스로 사용
    private final Map<Long, Post> posts = new HashMap<>();

    private Long nextId = 1L; //처음엔 id 1번부터 저장함

    public Post createPost(String title, String content){
        Long id = nextId++;

        Post post = new Post(id, title, content);
        posts.put(id, post);
        return post;
    }


    //post의 값들을 받아온 배열을 반환
    public List<Post> getPosts(){
        return new ArrayList<>(posts.values());
    }

    //한페이지를 출력하기 위한 메서드
    public Post getPost(Long id){
        return posts.get(id);
    }

    //수정 메서드
    public Post updatePost(Long id, String title, String content) {

        Post post = new Post(id, title, content);

        posts.put(id, post);

        return post;
    }

    //삭제 메서드
    public void deletePost(Long id) {
        posts.remove(id);
    }
}
