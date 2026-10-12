package com._62bestudy.presentation.filter;

import com._62bestudy.presentation.SessionAttributes;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class SessionIdFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        findSessionId(request)
                .ifPresent(sessionId -> request.setAttribute(
                        SessionAttributes.SESSION_ID,
                        sessionId
                ));

        filterChain.doFilter(request, response);
    }

    private Optional<String> findSessionId(HttpServletRequest request) {
        var headerSessionId = request.getHeader(SessionAttributes.SESSION_HEADER_NAME);
        if (headerSessionId != null && !headerSessionId.isBlank()) {
            return Optional.of(headerSessionId);
        }

        var cookies = request.getCookies();
        if (cookies == null) {
            return Optional.empty();
        }

        return Arrays.stream(cookies)
                .filter(cookie -> SessionAttributes.SESSION_COOKIE_NAME.equals(cookie.getName()))
                .map(Cookie::getValue)
                .filter(value -> !value.isBlank())
                .findFirst();
    }
}
