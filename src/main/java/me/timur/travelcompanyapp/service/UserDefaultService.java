package me.timur.travelcompanyapp.service;

import me.timur.travelcompanyapp.domain.User;
import me.timur.travelcompanyapp.exception.InvalidUsernameException;
import me.timur.travelcompanyapp.exception.ResourceNotFoundException;
import me.timur.travelcompanyapp.repository.UserRepository;
import me.timur.travelcompanyapp.security.auth.ApplicationUserRole;
import org.springframework.stereotype.Service;

/**
 * Created by Temurbek Ismoilov on 05/02/22.
 */

@Service("user_service_default")
public record UserDefaultService(
        UserRepository userRepository
) implements UserService{

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new InvalidUsernameException(String.format("Username %s not found", username)));
    }

    public User findByUsernameAndRole(String username, ApplicationUserRole role) {
        return userRepository.findByUsernameAndRole(username, role)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Could not find user with name %s and role %s" + username, role.name())));
    }
}
