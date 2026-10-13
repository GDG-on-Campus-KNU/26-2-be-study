package org.example.week1.presentation.dto;

public record ErrorResponse(
        int status,
        String message
) {
}
