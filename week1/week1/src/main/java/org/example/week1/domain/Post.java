package org.example.week1.domain;

import lombok.Getter;

@Getter
public class Post {
    Long id;
    String title;
    String content;

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
