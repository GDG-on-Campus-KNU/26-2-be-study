package com._62bestudy.application;

import com._62bestudy.application.exception.UserNotFoundException;
import com._62bestudy.domain.User;
import com._62bestudy.domain.UserRepository;
import com._62bestudy.domain.exception.InvalidUserException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final IdGenerator idGenerator;

    public User create(String name, String password) {
        var user = User.create(
                idGenerator.generate(),
                name,
                encodePassword(password)
        );

        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(
                        "유저를 찾을 수 없습니다."
                ));
    }

    public User update(String id, String name, String password) {
        var user = findById(id);

        user.update(
                name,
                encodePassword(password)
        );

        return userRepository.save(user);
    }

    public void delete(String id) {
        var user = findById(id);

        userRepository.delete(user);
    }

    private String encodePassword(String password) {
        if (password.isBlank()) {
            throw new InvalidUserException("비밀번호는 비어 있을 수 없습니다.");
        }

        return passwordEncoder.encode(password);
    }
}
