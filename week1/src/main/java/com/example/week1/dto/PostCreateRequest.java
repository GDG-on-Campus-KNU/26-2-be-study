package com.example.week1.dto;
//클라이언트의 요청을 서버에 보낼때 사용할 데이터의 모양을 정하는것 -->>> DTO

public class PostCreateRequest {
    //id는 서버가 만든다. 
    private String title;
    private String content;


    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    //Spring이 JSON을 java 객체로 바꿀 때 값을 넣어줘야하므로
    //set 메서드가 있어야함
    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
