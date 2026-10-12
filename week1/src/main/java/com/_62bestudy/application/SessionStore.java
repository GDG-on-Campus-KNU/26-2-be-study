package com._62bestudy.application;

import com._62bestudy.presentation.resolver.CurrentUser;
import java.util.Optional;

public interface SessionStore {

    void save(String sessionId, CurrentUser currentUser);

    Optional<CurrentUser> findById(String sessionId);

    void delete(String sessionId);
}
