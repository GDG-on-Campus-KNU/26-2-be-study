package com._62bestudy.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    boolean existsByName(String name);

    Optional<User> findByName(String name);
}
