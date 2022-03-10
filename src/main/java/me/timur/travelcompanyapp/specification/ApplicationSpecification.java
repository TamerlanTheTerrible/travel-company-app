package me.timur.travelcompanyapp.specification;

import me.timur.travelcompanyapp.domain.Application;
import me.timur.travelcompanyapp.domain.ApplicationType;
import me.timur.travelcompanyapp.domain.Group;
import me.timur.travelcompanyapp.domain.User;
import me.timur.travelcompanyapp.repository.ApplicationTypeRepository;
import me.timur.travelcompanyapp.security.auth.ApplicationUserRole;
import me.timur.travelcompanyapp.service.UserService;
import me.timur.travelcompanyapp.util.DateUtil;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.*;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Set;


/**
 * Created by Temurbek Ismoilov on 01/03/22.
 */


@Component
public record ApplicationSpecification(
        UserService userService,
        ApplicationTypeRepository applicationTypeRepository
) {

    public Specification<Application> getSpecification(HashMap<String, String> filters) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Specification<Application> specification = null;
        final Set<String> keySet = filters.keySet();

        if (keySet.size() == 0)
            return emptySpecification();

        for (String key: keySet) {
            if (specification == null) {
                specification = invokeMethod(filters, key);
            } else {
                specification.and(invokeMethod(filters, key));
            }
        }

        return specification;
    }

    private Specification<Application> invokeMethod(HashMap<String, String> filters, String key) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return (Specification<Application>) ApplicationSpecification.class.getDeclaredMethod(key, String.class).invoke(this, filters.get(key));
    }

    private Specification<Application> emptySpecification() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
    }

    private Specification<Application> fromDate(String fromDate) {
        return (root, query, criteriaBuilder) -> {
            Join<Application, Group> join = root.join("group");
            return criteriaBuilder.greaterThanOrEqualTo(join.get("arrival"), DateUtil.stringToDateTimeOrNull(fromDate));
        };
    }

    private Specification<Application> toDate(String toDate) {
        return (root, query, criteriaBuilder) -> {
            Join<Application, Group> join = root.join("group");
            return criteriaBuilder.lessThan(join.get("arrival"), DateUtil.stringToDateTimeOrNull(toDate));
        };
    }

    private Specification<Application> tourOperator(String userName) {
        User tourOperator = userService.findByUsernameAndRole(userName, ApplicationUserRole.TOUR_OPERATOR);
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("tourOperator"), tourOperator);
    }

    private Specification<Application> byType(String typeName) {
        ApplicationType type = applicationTypeRepository.findById(typeName).get();
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("type"), type);
    }

    private Specification<Application> groupNumber(String groupNumber) {
        return (root, query, criteriaBuilder) -> {
            Join<Application, Group> join = root.join("group");
            return criteriaBuilder.equal(join.get("groupNumber"), groupNumber);
        };
    }

    private Specification<Application> isActive(Boolean isActive) {
        return (root, query, criteriaBuilder) -> {
            Join<Application, Group> join = root.join("group");
            return criteriaBuilder.equal(join.get("isActive"), isActive);
        };
    }
}
