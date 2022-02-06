package me.timur.travelcompanyapp.service;

import me.timur.travelcompanyapp.domain.User;
import me.timur.travelcompanyapp.model.group.GroupRegistrationRequest;

/**
 * Created by Temurbek Ismoilov on 06/02/22.
 */

public interface GroupService {

    Integer register(GroupRegistrationRequest dto, User user);
}
