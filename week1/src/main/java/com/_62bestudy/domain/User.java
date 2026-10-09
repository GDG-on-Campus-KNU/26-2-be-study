package com._62bestudy.domain;

import com._62bestudy.domain.exception.InvalidUserException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    private User(String id, String name, String password) {
        validateName(name);
        validatePassword(password);

        this.id = id;
        this.name = name;
        this.password = password;
    }

    public static User create(String id, String name, String password) {
        return new User(id, name, password);
    }

    public void update(String name, String password) {
        validateName(name);
        validatePassword(password);

        this.name = name;
        this.password = password;
    }

    private void validateName(String name) {
        if (name.isBlank()) {
            throw new InvalidUserException("이름은 비어 있을 수 없습니다.");
        }
    }

    private void validatePassword(String password) {
        if (password.isBlank()) {
            throw new InvalidUserException("비밀번호는 비어 있을 수 없습니다.");
        }
    }
}
