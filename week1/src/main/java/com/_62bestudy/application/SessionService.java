package com._62bestudy.application;

import com._62bestudy.application.exception.InvalidLoginException;
import com._62bestudy.domain.User;
import com._62bestudy.domain.UserRepository;
import com._62bestudy.presentation.resolver.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final IdGenerator idGenerator;
    private final SessionStore sessionStore;

    public String login(String name, String password) {
        var user = userRepository.findByName(name)
                .orElseThrow(() -> new InvalidLoginException(
                        "이름 또는 비밀번호가 올바르지 않습니다."
                ));

        validatePassword(password, user);

        var sessionId = idGenerator.generate();
        sessionStore.save(
                sessionId,
                new CurrentUser(
                        user.getId(),
                        user.getName()
                )
        );

        return sessionId;
    }

    public void logout(String sessionId) {
        sessionStore.delete(sessionId);
    }

    private void validatePassword(String password, User user) {
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidLoginException("이름 또는 비밀번호가 올바르지 않습니다.");
        }
    }
}
