package com._62bestudy.presentation.resolver;

import com._62bestudy.presentation.config.SessionProperties;
import com._62bestudy.presentation.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final SessionProperties sessionProperties;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginUser.class)
                && CurrentUser.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) {
        var currentUser = webRequest.getAttribute(
                sessionProperties.currentUserAttribute(),
                NativeWebRequest.SCOPE_REQUEST
        );

        if (currentUser == null) {
            throw new UnauthorizedException("로그인이 필요합니다.");
        }

        return currentUser;
    }
}
