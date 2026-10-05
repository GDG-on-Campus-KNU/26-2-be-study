package org.example.week1.domain.post.dto;

import lombok.Getter;
import org.example.week1.domain.post.Post;

@Getter
public class PostResponse {
    private final Long id;
    private final String title;
    private final String content;

    private PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
    }

    public static PostResponse from(Post post) {
        return new PostResponse(post);
    }
}
