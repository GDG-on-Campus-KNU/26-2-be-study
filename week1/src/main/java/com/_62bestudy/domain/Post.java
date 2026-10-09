package com._62bestudy.domain;

import com._62bestudy.domain.exception.InvalidPostException;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class Post {

    private static final int MAX_TITLE_LENGTH = 100;
    private static final int MAX_BODY_LENGTH = 5000;

    private String id;
    private String title;
    private String body;
    private LocalDateTime createdAt;

    public Post(String id, String title, String body, LocalDateTime createdAt) {
        validateTitle(title);
        validateBody(body);

        this.id = id;
        this.title = title;
        this.body = body;
        this.createdAt = createdAt;
    }

    public void update(String title, String body) {
        validateTitle(title);
        validateBody(body);

        this.title = title;
        this.body = body;
    }

    private void validateTitle(String title) {
        if (title.isBlank()) {
            throw new InvalidPostException("제목은 비어 있을 수 없습니다.");
        }

        if (title.length() > MAX_TITLE_LENGTH) {
            throw new InvalidPostException("제목은 100자를 초과할 수 없습니다.");
        }
    }

    private void validateBody(String body) {
        if (body.isBlank()) {
            throw new InvalidPostException("본문은 비어 있을 수 없습니다.");
        }

        if (body.length() > MAX_BODY_LENGTH) {
            throw new InvalidPostException("본문은 5000자를 초과할 수 없습니다.");
        }
    }
}
