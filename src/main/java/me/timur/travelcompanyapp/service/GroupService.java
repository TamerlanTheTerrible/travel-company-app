package me.timur.travelcompanyapp.service;

import me.timur.travelcompanyapp.entity.Group;
import me.timur.travelcompanyapp.entity.User;
import me.timur.travelcompanyapp.model.reservation.pre.GroupRegistrationRequest;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Temurbek Ismoilov on 06/02/22.
 */

public interface GroupService {

    Integer register(GroupRegistrationRequest dto, User user);

    Group findById(Integer id);
}
