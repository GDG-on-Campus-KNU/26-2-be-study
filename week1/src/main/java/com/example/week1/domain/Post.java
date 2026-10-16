package com.example.week1.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "posts")
public class Post {
    /*
     * 이제 직접 ID를 생성하는 것이 아닌, 식별자 생성에 데이터베이스의 IDENTITY방식을 사용하겠다는 뜻.
     * 
     * @ID는 이 필드가 Entity의 식별자라는 뜻
     * MYSQL에서 IDENTITY의 방식으로 AUTO_INCREMENT를 일반적으로 사용함.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    // JPA에서 사용하는 기본 생성자
    /*
     * JPA가 Entity를 생성할 때 사용하는 생성자
     * JPA 명세에서는 public 또는 protected 접근 수준의 매개변수가 없는 생성자를 요구함
     */
    protected Post() {
    }

    // 사용자가 새로운 글을 생성할때 사용하는 생성자
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // id, title, content는 private이므로 get_가 필요함!
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    /*
     * 게시글 수정 메서드를 Entity 안에 만들어줌!
     * JPA의 Dirty Checking(변경감지)와 연결됨
     */
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
