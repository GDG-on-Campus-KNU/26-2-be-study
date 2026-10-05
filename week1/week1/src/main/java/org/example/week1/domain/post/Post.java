package org.example.week1.domain.post;

import lombok.Getter;

@Getter
public class Post {
    Long id;
    String title;
    String content;

    public Post(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
