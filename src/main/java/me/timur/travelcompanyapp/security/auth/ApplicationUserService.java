package me.timur.travelcompanyapp.security.auth;

import me.timur.travelcompanyapp.entity.User;
import me.timur.travelcompanyapp.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by Temurbek Ismoilov on 28/01/22.
 */

@Service
public class ApplicationUserService implements UserDetailsService {

    private final UserService userService;

    public ApplicationUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        return ApplicationUser.fromEntity(user);
    }
}
