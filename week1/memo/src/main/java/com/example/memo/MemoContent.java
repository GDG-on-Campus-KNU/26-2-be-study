package com.example.memo;

import jakarta.validation.constraints.NotBlank;

public record MemoContent(
        @NotBlank(message = "content should not be empty string")
        String content
) {
}
