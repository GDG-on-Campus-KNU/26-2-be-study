package com._62bestudy.presentation.dto;

import com._62bestudy.domain.User;

public record UserResponse(
        String id,
        String name
) {

    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getName()
        );
    }
}
