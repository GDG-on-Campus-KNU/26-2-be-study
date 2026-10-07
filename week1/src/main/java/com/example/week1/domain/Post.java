package com.example.week1.domain;


public class Post {

    private Long id;
    private String title;
    private String content;

    public Post(Long id, String title, String content){ 
        this.id = id;
        this.title = title;
        this.content = content;
    }

    //id, title, content는 private이므로 get_가 필요함!
    public Long getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getContent(){
        return content;
    }
    
}
