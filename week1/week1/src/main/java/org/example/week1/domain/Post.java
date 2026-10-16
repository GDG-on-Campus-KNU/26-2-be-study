package org.example.week1.domain;

import lombok.Getter;

@Getter
public class Post {
    String id;
    String title;
    String content;

    public Post (String id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
