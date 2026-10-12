package com._62bestudy.presentation.dto;

import jakarta.validation.constraints.NotNull;

public record PostCreateRequest(
        @NotNull(message = "제목은 필수입니다.")
        String title,

        @NotNull(message = "본문은 필수입니다.")
        String body
) {
}
