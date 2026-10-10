package org.example.week1.presentation.dto;

import org.example.week1.domain.Post;

public record PostResponse(
        String id,
        String title,
        String content
) {
    public static PostResponse from(Post post) {
        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent()
        );
    }
}
