package me.timur.travelcompanyapp.service;

import me.timur.travelcompanyapp.entity.Group;
import me.timur.travelcompanyapp.entity.User;
import me.timur.travelcompanyapp.entity.Application;
import me.timur.travelcompanyapp.entity.ApplicationType;
import me.timur.travelcompanyapp.model.reservation.post.ApplicationPostRegistrationDto;
import me.timur.travelcompanyapp.model.reservation.post.Reserved;
import me.timur.travelcompanyapp.model.reservation.pre.ApplicationPreRegistrationRequest;
import me.timur.travelcompanyapp.repository.ApplicationRepository;
import me.timur.travelcompanyapp.repository.ApplicationTypeRepository;
import me.timur.travelcompanyapp.security.auth.ApplicationUserRole;
import me.timur.travelcompanyapp.specification.ApplicationSpecification;
import me.timur.travelcompanyapp.specification.SpecificationBuilder;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.data.jpa.domain.Specification;
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
    public Application save(ApplicationPreRegistrationRequest appCreateDto) {
        //save application
        Group group = groupService.findById(appCreateDto.getGroupId());
        User tourOperator = userService.findByUsernameAndRole(appCreateDto.getTourOperatorName(), ApplicationUserRole.TOUR_OPERATOR);
        ApplicationType appType = applicationTypeRepository.getById(appCreateDto.getApplicationType());
        Application app = applicationRepository.save(new Application(group, appType));
        //book services
        ReservationService reservationService = beanFactory.getBean(appCreateDto.getApplicationType().toLowerCase() + "ReservationService", ReservationService.class);
        reservationService.reserveAll(app, appCreateDto.getBookingList());

        return app;
    }

    @Override
    public List<ApplicationType> findAllTypes() {
        return applicationTypeRepository.findAll();
    }

    @Override
    public List<ApplicationPostRegistrationDto> findAllFiltered(HashMap<String, String> filters) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        //Get  application list
        final Specification<Application> specification = SpecificationBuilder.build(applicationSpecification, filters);
        final List<Application> applications = applicationRepository.findAll(specification);

        //get corresponding reservations' DTOs. The DTOs are grouped by application ID
        final ReservationService reservationService = beanFactory.getBean(applications.get(0).getType().getName().toLowerCase() + "ReservationService", ReservationService.class);
        final HashMap<Integer, List<Reserved>> reservations = reservationService.getAllByApplicationList(applications);

        return ApplicationPostRegistrationDto.fromEntityList(applications, reservations);
    }

}
