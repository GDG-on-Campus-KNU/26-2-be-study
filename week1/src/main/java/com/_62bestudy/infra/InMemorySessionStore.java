package com._62bestudy.infra;

import com._62bestudy.application.SessionStore;
import com._62bestudy.presentation.resolver.CurrentUser;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

@Component
public class InMemorySessionStore implements SessionStore {

    private final Map<String, CurrentUser> sessions = new ConcurrentHashMap<>();

    @Override
    public void save(String sessionId, CurrentUser currentUser) {
        sessions.put(sessionId, currentUser);
    }

    @Override
    public Optional<CurrentUser> findById(String sessionId) {
        return Optional.ofNullable(sessions.get(sessionId));
    }

    @Override
    public void delete(String sessionId) {
        sessions.remove(sessionId);
    }
}
