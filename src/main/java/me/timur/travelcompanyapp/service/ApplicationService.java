package me.timur.travelcompanyapp.service;

import me.timur.travelcompanyapp.entity.Application;
import me.timur.travelcompanyapp.entity.ApplicationType;
import me.timur.travelcompanyapp.model.reservation.pre.ApplicationPreRegistrationRequest;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Temurbek Ismoilov on 08/02/22.
 */

public interface ApplicationService {

    @Transactional
    Application save(ApplicationPreRegistrationRequest applicationPreRegistrationRequest);

    List<ApplicationType> findAllTypes();

    List<Application> findAllFiltered(HashMap<String, String> filters) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException;
}
