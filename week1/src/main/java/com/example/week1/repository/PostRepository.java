package com.example.week1.repository;

import org.springframework.data.jpa.repository.JpaRepository; //Spring Data JPA에서 제공하는 JpaRepository 인터페이스를 가져옴
import com.example.week1.domain.Post; //Entity 클래스를 import함

public interface PostRepository extends JpaRepository<Post, Long>{ //Long 은 id 타입과 일치해야함.
    //그냥 인터페이스만 구현해놓으면 Spring Data JPA가 알아서 Repository 구현 객체를 제공함.
}