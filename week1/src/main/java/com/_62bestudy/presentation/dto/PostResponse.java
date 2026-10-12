package com._62bestudy.presentation.dto;

import com._62bestudy.domain.Post;

public record PostResponse(
        String id,
        String title,
        String body
) {

    public static PostResponse from(Post post) {
        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getBody()
        );
    }
}
