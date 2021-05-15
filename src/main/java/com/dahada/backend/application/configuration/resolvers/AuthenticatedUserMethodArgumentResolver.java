package com.dahada.backend.application.configuration.resolvers;

import com.dahada.backend.application.auth.SecurityContextHolder;
import com.dahada.backend.application.auth.dto.Authentication;
import com.dahada.backend.application.configuration.resolvers.annotations.Authenticated;
import com.dahada.backend.application.user.UserInfo;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Optional;

public class AuthenticatedUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Authenticated.class)
                && parameter.getParameterType().isAssignableFrom(UserInfo.class);
    }

    @Nullable
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        final Optional<Authentication> maybeAuthentication = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
        return maybeAuthentication.<Object>map(Authentication::getUserDetails).orElse(null);
    }
}
