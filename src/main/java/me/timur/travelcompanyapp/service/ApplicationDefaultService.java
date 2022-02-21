package me.timur.travelcompanyapp.service;

import me.timur.travelcompanyapp.domain.application.ApplicationType;
import me.timur.travelcompanyapp.repository.ApplicationTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 08/02/22.
 */

@Service
public record ApplicationDefaultService(
        ApplicationTypeRepository applicationTypeRepository
) implements ApplicationService {

    @Override
    public List<ApplicationType> findAllTypes() {
        return applicationTypeRepository.findAll();
    }
}
