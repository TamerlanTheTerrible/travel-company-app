package me.timur.travelcompanyapp.service;

import me.timur.travelcompanyapp.domain.Group;
import me.timur.travelcompanyapp.domain.User;
import me.timur.travelcompanyapp.domain.application.Application;
import me.timur.travelcompanyapp.domain.application.ApplicationType;
import me.timur.travelcompanyapp.model.ApplicationCreateRequest;
import me.timur.travelcompanyapp.repository.ApplicationTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 08/02/22.
 */

@Service
public record ApplicationDefaultService(
        BookingService bookingService,
        GroupService groupService,
        UserService userService,
        ApplicationTypeRepository applicationTypeRepository
) implements ApplicationService {

    @Override
    public Application saveAll(ApplicationCreateRequest applicationCreateRequest) {
        Group group = groupService.findById(applicationCreateRequest.getGroupId());
        User tourOperator = userService.findByUserame()
    }

    @Override
    public List<ApplicationType> findAllTypes() {
        return applicationTypeRepository.findAll();
    }
}
