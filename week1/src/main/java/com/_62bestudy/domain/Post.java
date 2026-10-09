package com._62bestudy.domain;

import com._62bestudy.domain.exception.InvalidPostException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
@Entity
@Table(name = "posts")
public class Post {

    private static final int MAX_TITLE_LENGTH = 100;
    private static final int MAX_BODY_LENGTH = 5000;

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private String id;

    @Column(name = "title", nullable = false, length = MAX_TITLE_LENGTH)
    private String title;

    @Column(name = "body", nullable = false, length = MAX_BODY_LENGTH)
    private String body;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    protected Post() {
    }

    private Post(String id, String title, String body, LocalDateTime createdAt) {
        validateTitle(title);
        validateBody(body);

        this.id = id;
        this.title = title;
        this.body = body;
        this.createdAt = createdAt;
    }

    public static Post create(String id, String title, String body, LocalDateTime createdAt) {
        return new Post(id, title, body, createdAt);
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
