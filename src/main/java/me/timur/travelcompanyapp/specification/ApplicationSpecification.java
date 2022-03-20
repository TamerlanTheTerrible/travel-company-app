package me.timur.travelcompanyapp.specification;

import me.timur.travelcompanyapp.entity.Application;
import me.timur.travelcompanyapp.entity.ApplicationType;
import me.timur.travelcompanyapp.entity.Group;
import me.timur.travelcompanyapp.entity.User;
import me.timur.travelcompanyapp.repository.ApplicationTypeRepository;
import me.timur.travelcompanyapp.security.auth.ApplicationUserRole;
import me.timur.travelcompanyapp.service.UserService;
import me.timur.travelcompanyapp.util.DateUtil;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.*;

/**
 * Created by Temurbek Ismoilov on 01/03/22.
 */


@Component
public record ApplicationSpecification(
        UserService userService,
        ApplicationTypeRepository applicationTypeRepository
) implements EntitySpecification {

    public Specification<Application> fromDate(String fromDate) {
        return (root, query, criteriaBuilder) -> {
            Join<Application, Group> join = root.join("group");
            return criteriaBuilder.greaterThanOrEqualTo(join.get("arrival"), DateUtil.stringToDateTimeOrNull(fromDate));
        };
    }

    public Specification<Application> toDate(String toDate) {
        return (root, query, criteriaBuilder) -> {
            Join<Application, Group> join = root.join("group");
            return criteriaBuilder.lessThan(join.get("arrival"), DateUtil.stringToDateTimeOrNull(toDate));
        };
    }

    public Specification<Application> tourOperator(String userName) {
        User tourOperator = userService.findByUsernameAndRole(userName, ApplicationUserRole.TOUR_OPERATOR);
        return (root, query, criteriaBuilder) -> {
            Join<Application, Group> join = root.join("group");
            return criteriaBuilder.equal(join.get("tourOperator"), tourOperator);
        };
    }

    public Specification<Application> type(String typeName) {
        ApplicationType type = applicationTypeRepository.findById(typeName.toUpperCase()).get();
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("type"), type);
    }

    public Specification<Application> groupId(String groupId) {
        return (root, query, criteriaBuilder) -> {
            Join<Application, Group> join = root.join("group");
            return criteriaBuilder.equal(join.get("id"), groupId);
        };
    }

    public Specification<Application> groupNumber(String groupNumber) {
        return (root, query, criteriaBuilder) -> {
            Join<Application, Group> join = root.join("group");
            return criteriaBuilder.equal(join.get("groupNumber"), groupNumber);
        };
    }

    public Specification<Application> isActive(Boolean isActive) {
        return (root, query, criteriaBuilder) -> {
            Join<Application, Group> join = root.join("group");
            return criteriaBuilder.equal(join.get("isActive"), isActive);
        };
    }
}
