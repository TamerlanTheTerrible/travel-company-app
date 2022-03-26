package me.timur.travelcompanyapp.service;

import me.timur.travelcompanyapp.entity.Group;
import me.timur.travelcompanyapp.entity.User;
import me.timur.travelcompanyapp.model.reservation.pre.GroupRegistrationRequest;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Temurbek Ismoilov on 06/02/22.
 */

public interface GroupService {

    Integer register(GroupRegistrationRequest dto, User user);

    void cancel(Integer id, User user);

    Group findById(Integer id);

    List<Group> findAll(HashMap<String, String> filters);
}
