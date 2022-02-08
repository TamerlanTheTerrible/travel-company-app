package me.timur.travelcompanyapp.service;

import me.timur.travelcompanyapp.domain.ApplicationType;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 08/02/22.
 */

public interface ApplicationService {
    List<ApplicationType> findAllTypes();
}
