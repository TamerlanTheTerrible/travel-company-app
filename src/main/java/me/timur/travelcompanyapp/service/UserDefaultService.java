package me.timur.travelcompanyapp.service;

import me.timur.travelcompanyapp.domain.User;
import me.timur.travelcompanyapp.exception.InvalidUsernameException;
import me.timur.travelcompanyapp.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * Created by Temurbek Ismoilov on 05/02/22.
 */

@Service("user_service_default")
public class UserDefaultService implements UserService{

    private final UserRepository userRepository;

    public UserDefaultService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUserName(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new InvalidUsernameException(String.format("Username %s not found", username)));
    }
}
