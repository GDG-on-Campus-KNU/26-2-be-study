package com._62bestudy.domain;

import com._62bestudy.domain.exception.InvalidPostException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "posts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {

    private static final int MAX_TITLE_LENGTH = 100;
    private static final int MAX_BODY_LENGTH = 5000;

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "body", nullable = false)
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    private Post(String id, String title, String body, User author) {
        validateTitle(title);
        validateBody(body);

        this.id = id;
        this.title = title;
        this.body = body;
        this.author = author;
    }

    public void update(String title, String body) {
        validateTitle(title);
        validateBody(body);

        this.title = title;
        this.body = body;
    }

    public boolean isAuthor(String userId) {
        return author.getId().equals(userId);
    }

    public static Post create(String id, String title, String body, User author) {
        return new Post(id, title, body, author);
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
