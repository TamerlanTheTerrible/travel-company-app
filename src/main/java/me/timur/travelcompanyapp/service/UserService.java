package me.timur.travelcompanyapp.service;

import me.timur.travelcompanyapp.domain.User;

/**
 * Created by Temurbek Ismoilov on 05/02/22.
 */

public interface UserService {

    User findByUsername(String username);
}
