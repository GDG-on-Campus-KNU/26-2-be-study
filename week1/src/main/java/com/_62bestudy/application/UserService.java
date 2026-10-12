package com._62bestudy.application;

import com._62bestudy.application.exception.DuplicateUserNameException;
import com._62bestudy.application.exception.UserNotFoundException;
import com._62bestudy.domain.User;
import com._62bestudy.domain.UserRepository;
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
        validateDuplicateName(name);

        var id = idGenerator.generate();
        var encodedPassword = passwordEncoder.encode(password);

        var user = User.create(
                id,
                name,
                encodedPassword
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

    public User update(String id, String newName, String password) {
        var user = findById(id);
        var userName = user.getName();

        if (!userName.equals(newName)) {
            validateDuplicateName(newName);
        }

        user.update(
                newName,
                passwordEncoder.encode(password)
        );

        return userRepository.save(user);
    }

    public void delete(String id) {
        var user = findById(id);

        userRepository.delete(user);
    }

    private void validateDuplicateName(String name) {
        if (userRepository.existsByName(name)) {
            throw new DuplicateUserNameException("이미 사용 중인 이름입니다.");
        }
    }
}
