package me.timur.travelcompanyapp.service;

import me.timur.travelcompanyapp.domain.Application;
import me.timur.travelcompanyapp.domain.ApplicationType;
import me.timur.travelcompanyapp.domain.User;
import me.timur.travelcompanyapp.model.ApplicationCreateRequest;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Temurbek Ismoilov on 08/02/22.
 */

public interface ApplicationService {

    @Transactional
    Application save(ApplicationCreateRequest applicationCreateRequest);

    List<ApplicationType> findAllTypes();

    List<Application> findAllFiltered(HashMap<String, String> filters) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException;
}
