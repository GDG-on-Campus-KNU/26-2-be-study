package com.example.week1.dto;

import com.example.week1.domain.Post;
//Service가 DTO 변환을 한 뒤에 Controller에 준다
public record PostResponse(
    /* class가 아니라 record
    record는 데이터를 담는 용도의 클래스를 간결하게 정의할 수 있도록 Java가 제공하는 문법 : 생성자, 뭐 이런것들 생략해도 됨. getter 사용할 필요 없음.
    private final 필드로 만들어준다
     */
    Long id,
    String title,
    String content
    ) {
    
    /* static은 메서드가 객체가 아닌 클래스에 속한다는 뜻. 객체 만들 필요 없이 그냥 바로 호출 가능.
    변환 작업을 transaction 안에서 계속 수행하지 않고 한번만 하기 위해서 변환 메서드를 만든 것.
     */
    public static PostResponse from(Post post) {
        return new PostResponse(
            post.getId(),
            post.getTitle(),
            post.getContent()
        );
    }
}
