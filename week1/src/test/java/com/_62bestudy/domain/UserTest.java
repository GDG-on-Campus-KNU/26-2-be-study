package com._62bestudy.domain;

import static org.junit.jupiter.api.Assertions.*;

import com._62bestudy.domain.exception.InvalidUserException;
import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    void 유저를_생성한다() {
        var id = "user-id";
        var name = "홍길동";
        var password = "encoded-password";

        var user = User.create(id, name, password);

        assertEquals(id, user.getId());
        assertNull(user.getCreatedAt());
        assertNull(user.getUpdatedAt());
        assertEquals(name, user.getName());
        assertEquals(password, user.getPassword());
    }

    @Test
    void 유저를_수정한다() {
        var user = createUser();

        user.update("김철수", "changed-password");

        assertEquals("김철수", user.getName());
        assertEquals("changed-password", user.getPassword());
    }

    @Test
    void 이름이_비어_있으면_예외가_발생한다() {
        assertThrows(
                InvalidUserException.class,
                () -> User.create(
                        "user-id",
                        " ",
                        "encoded-password"
                )
        );
    }

    @Test
    void 비밀번호가_비어_있으면_예외가_발생한다() {
        assertThrows(
                InvalidUserException.class,
                () -> User.create(
                        "user-id",
                        "홍길동",
                        " "
                )
        );
    }

    private User createUser() {
        return User.create(
                "user-id",
                "홍길동",
                "encoded-password"
        );
    }
}
