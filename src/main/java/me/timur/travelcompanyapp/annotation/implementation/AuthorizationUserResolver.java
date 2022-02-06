package me.timur.travelcompanyapp.annotation.implementation;

import me.timur.travelcompanyapp.annotation.AuthorizationUser;
import me.timur.travelcompanyapp.domain.User;
import me.timur.travelcompanyapp.security.jwt.JwtTokenVerifier;
import me.timur.travelcompanyapp.service.UserService;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Temurbek Ismoilov on 06/02/22.
 */

@Component
public class AuthorizationUserResolver implements HandlerMethodArgumentResolver {

    private final JwtTokenVerifier jwtTokenVerifier;
    private final UserService userService;

    public AuthorizationUserResolver(JwtTokenVerifier jwtTokenVerifier, UserService userService) {
        this.jwtTokenVerifier = jwtTokenVerifier;
        this.userService = userService;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(AuthorizationUser.class) != null;
    }

    @Override
    public User resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String token = request.getHeader("Authorization");
        String username = jwtTokenVerifier.getUsername(token);
        return userService.findByUserName(username);
    }
}
