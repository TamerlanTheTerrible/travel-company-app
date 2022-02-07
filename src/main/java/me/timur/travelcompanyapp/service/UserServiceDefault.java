package me.timur.travelcompanyapp.service;

import ch.qos.logback.core.joran.conditional.IfAction;
import me.timur.travelcompanyapp.domain.User;
import me.timur.travelcompanyapp.exception.InvalidUsernameException;
import me.timur.travelcompanyapp.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Temurbek Ismoilov on 05/02/22.
 */

@Service("user_service_default")
public class UserServiceDefault implements UserService{

    private final UserRepository userRepository;

    public UserServiceDefault(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUserName(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new InvalidUsernameException(String.format("Username %s not found", username)));
    }
}
