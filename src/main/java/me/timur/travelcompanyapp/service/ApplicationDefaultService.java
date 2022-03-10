package me.timur.travelcompanyapp.service;

import me.timur.travelcompanyapp.domain.Group;
import me.timur.travelcompanyapp.domain.User;
import me.timur.travelcompanyapp.domain.Application;
import me.timur.travelcompanyapp.domain.ApplicationType;
import me.timur.travelcompanyapp.model.ApplicationCreateRequest;
import me.timur.travelcompanyapp.repository.ApplicationRepository;
import me.timur.travelcompanyapp.repository.ApplicationTypeRepository;
import me.timur.travelcompanyapp.security.auth.ApplicationUserRole;
import me.timur.travelcompanyapp.specification.ApplicationSpecification;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Temurbek Ismoilov on 08/02/22.
 */

@Service
public class ApplicationDefaultService implements ApplicationService {

    private final BeanFactory beanFactory;
    private final GroupService groupService;
    private final UserService userService;
    private final ApplicationRepository applicationRepository;
    private final ApplicationTypeRepository applicationTypeRepository;
    private final ApplicationSpecification applicationSpecification;

    public ApplicationDefaultService(BeanFactory beanFactory, GroupService groupService, UserService userService, ApplicationRepository applicationRepository, ApplicationTypeRepository applicationTypeRepository, ApplicationSpecification applicationSpecification) {
        this.beanFactory = beanFactory;
        this.groupService = groupService;
        this.userService = userService;
        this.applicationRepository = applicationRepository;
        this.applicationTypeRepository = applicationTypeRepository;
        this.applicationSpecification = applicationSpecification;
    }

    @Override
    public Application save(ApplicationCreateRequest appCreateDto) {
        //save application
        Group group = groupService.findById(appCreateDto.getGroupId());
        User tourOperator = userService.findByUsernameAndRole(appCreateDto.getTourOperatorName(), ApplicationUserRole.TOUR_OPERATOR);
        ApplicationType appType = applicationTypeRepository.getById(appCreateDto.getApplicationType());
        Application app = applicationRepository.save(new Application(group, appType, tourOperator));
        //book services
        BookingService bookingService = beanFactory.getBean(appCreateDto.getApplicationType().toLowerCase() + "BookingService", BookingService.class);
        bookingService.bookAll(app, appCreateDto.getBookingList());

        return app;
    }

    @Override
    public List<ApplicationType> findAllTypes() {
        return applicationTypeRepository.findAll();
    }

    @Override
    public List<Application> findAllFiltered(HashMap<String, String> filters) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        return applicationRepository.findAll(applicationSpecification.getSpecification(filters));
    }
}
