package com._62bestudy.presentation.dto;

import jakarta.validation.constraints.NotBlank;

public record UserLoginRequest(
        @NotBlank(message = "이름은 필수입니다.")
        String name,

        @NotBlank(message = "비밀번호는 필수입니다.")
        String password
) {
}
