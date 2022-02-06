package me.timur.travelcompanyapp.configuration;

import me.timur.travelcompanyapp.annotation.implementation.AuthorizationUserResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 06/02/22.
 */

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    private final AuthorizationUserResolver authorizationUserResolver;

    public WebMvcConfig(AuthorizationUserResolver authorizationUserResolver) {
        this.authorizationUserResolver = authorizationUserResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(authorizationUserResolver);
    }
}
