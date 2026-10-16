package com.example.week1.service;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service; //@Service 어노테이션을 쓰기 위한 import문
import org.springframework.web.server.ResponseStatusException;

import com.example.week1.domain.Post; //domain에 있는 Post.java파일을 가져오기 위함
import com.example.week1.repository.PostRepository;
import com.example.week1.dto.PostResponse;
import org.springframework.transaction.annotation.Transactional;

@Service 
public class PostService {
    /*
    의존성 주입(DI), 인터페이스는 직접 인스턴스화할 수 없음. 근데 Spring Data JPA가 Repository 구현 객체를 제공하고
    Spring이 이를 bean으로 등록. -> 인스턴스화 가능!
    @transaction은 뭐냐? -> 여러 데이터 베이스 작업을 하나의 논리적인 작업 단위로 묶어 해결! 
     */
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    //게시글 등록
    //Post라는 Entity를 직접 반환하는게 아니라 PostResponse라는 DTO로 반환. 원하는 정보만 반환하거나 데이터베이스 정보 그대로 새는 것을 방지
    @Transactional
    public PostResponse createPost(String title, String content){
        Post post = new Post(title, content);
        Post savedPost = postRepository.save(post);

        return PostResponse.from(savedPost);
    }

    //모두 조회
    //프레임워크가 알아서 SQL실행과 객체 매핑작업을 해줌, 정렬은 따로 구현해줘야함..
    //Entity를 DTO로 변환하는걸 반복문 안쓰고 Stream API 사용!
    @Transactional(readOnly = true)
    public List<PostResponse> getPosts() {
        return postRepository.findAll() //모든 게시글 Entity 조회
                .stream() //리스트를 stream으로 변환
                .map(PostResponse::from) //각 Entity에 from 메서드 적용
                .toList();
    }

    //단건 조회
    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = findPostOrThrow(id);

        return PostResponse.from(post);
    }

    /*
    Optional<Post>이런 반환 타입을 가지는데 해당 id에 대한 게시글이 존재하지 않을 때 orElseThrow를 써서 예외처리를 해줌!
    존재하지 않는 데이터의 처리도 가능
    */
    private Post findPostOrThrow(Long id) {
    return postRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, //HTTP 404
            "게시글을 찾을 수 없습니다. id=" + id
        ));
    }

    //수정 메서드
    @Transactional
    public PostResponse updatePost(Long id, String title, String content) {
        Post post = findPostOrThrow(id); //해당 ID의 게시글을 찾고 존재하지 않으면 404를 반환!

        post.update(title, content);
         /*
        Dirty Checking! Java 객체의 필드 값만 바꾸어도 MySQL 데이터까지 수정될 수 있음 굉장히 편리한 기능
        Hibernate는 Entity의 상태를 추적할 수 있음!
        */
        return PostResponse.from(post);
    }

    //삭제 메서드
    @Transactional
    public void deletePost(Long id) {
        Post post = findPostOrThrow(id); //존재하는지 확인하고 없으면 404!

        postRepository.delete(post);
    }
}
