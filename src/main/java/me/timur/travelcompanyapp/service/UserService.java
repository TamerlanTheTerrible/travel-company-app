package me.timur.travelcompanyapp.service;

import me.timur.travelcompanyapp.domain.User;
import me.timur.travelcompanyapp.security.auth.ApplicationUserRole;

/**
 * Created by Temurbek Ismoilov on 05/02/22.
 */

public interface UserService {

    User findByUsername(String username);

    User findByUsernameAndRole(String username, ApplicationUserRole role);
}
