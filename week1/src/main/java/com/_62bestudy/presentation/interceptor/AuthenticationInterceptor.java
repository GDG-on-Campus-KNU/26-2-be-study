package com._62bestudy.presentation.interceptor;

import com._62bestudy.application.SessionStore;
import com._62bestudy.presentation.SessionAttributes;
import com._62bestudy.presentation.exception.UnauthorizedException;
import com._62bestudy.presentation.resolver.LoginUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {

    private final SessionStore sessionStore;

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        var handlerMethod = (HandlerMethod) handler;

        if (!requiresLogin(handlerMethod)) {
            return true;
        }

        var sessionId = (String) request.getAttribute(SessionAttributes.SESSION_ID);
        if (sessionId == null || sessionId.isBlank()) {
            throw new UnauthorizedException("로그인이 필요합니다.");
        }

        var currentUser = sessionStore.findById(sessionId)
                .orElseThrow(() -> new UnauthorizedException("로그인이 필요합니다."));

        request.setAttribute(SessionAttributes.CURRENT_USER, currentUser);

        return true;
    }

    private boolean requiresLogin(HandlerMethod handlerMethod) {
        for (var parameter : handlerMethod.getMethodParameters()) {
            if (parameter.hasParameterAnnotation(LoginUser.class)) {
                return true;
            }
        }

        return false;
    }
}
