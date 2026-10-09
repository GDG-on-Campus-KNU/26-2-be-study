package com._62bestudy.application;

import static org.junit.jupiter.api.Assertions.*;

import com._62bestudy.application.exception.DuplicateUserNameException;
import com._62bestudy.application.exception.UserNotFoundException;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void 유저를_생성한다() {
        var user = userService.create(
                "생성유저",
                "password"
        );

        assertNotNull(user.getId());
        var id = assertDoesNotThrow(() -> UUID.fromString(user.getId()));
        assertEquals(7, id.version());
        assertNotNull(user.getCreatedAt());
        assertNotNull(user.getUpdatedAt());
        assertEquals("생성유저", user.getName());
        assertNotEquals("password", user.getPassword());
        assertTrue(passwordEncoder.matches("password", user.getPassword()));
    }

    @Test
    void 유저를_조회한다() {
        var created = userService.create(
                "조회유저",
                "password"
        );

        var found = userService.findById(created.getId());

        assertEquals(created.getId(), found.getId());
        assertEquals(created.getName(), found.getName());
        assertTrue(passwordEncoder.matches("password", found.getPassword()));
    }

    @Test
    void 유저를_수정한다() {
        var created = userService.create(
                "수정유저",
                "password"
        );

        var updated = userService.update(
                created.getId(),
                "김철수",
                "changed-password"
        );

        assertEquals("김철수", updated.getName());
        assertTrue(passwordEncoder.matches("changed-password", updated.getPassword()));
    }

    @Test
    void 이름이_중복되면_예외가_발생한다() {
        userService.create(
                "중복유저",
                "password"
        );

        assertThrows(
                DuplicateUserNameException.class,
                () -> userService.create(
                        "중복유저",
                        "password"
                )
        );
    }

    @Test
    void 다른_유저의_이름으로_수정하면_예외가_발생한다() {
        var user = userService.create(
                "원래유저",
                "password"
        );
        userService.create(
                "이미있는유저",
                "password"
        );

        assertThrows(
                DuplicateUserNameException.class,
                () -> userService.update(
                        user.getId(),
                        "이미있는유저",
                        "password"
                )
        );
    }

    @Test
    void 존재하지_않는_유저를_조회하면_예외가_발생한다() {
        var id = "not-exist-id";

        assertThrows(
                UserNotFoundException.class,
                () -> userService.findById(id)
        );
    }

    @Test
    void 존재하지_않는_유저를_삭제하면_예외가_발생한다() {
        var id = "not-exist-id";

        assertThrows(
                UserNotFoundException.class,
                () -> userService.delete(id)
        );
    }
}
