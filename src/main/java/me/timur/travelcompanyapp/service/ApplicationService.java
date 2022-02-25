package me.timur.travelcompanyapp.service;

import me.timur.travelcompanyapp.domain.application.Application;
import me.timur.travelcompanyapp.domain.application.ApplicationType;
import me.timur.travelcompanyapp.model.ApplicationCreateRequest;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 08/02/22.
 */

public interface ApplicationService {

    Application saveAll(ApplicationCreateRequest applicationCreateRequest);

    List<ApplicationType> findAllTypes();
}
