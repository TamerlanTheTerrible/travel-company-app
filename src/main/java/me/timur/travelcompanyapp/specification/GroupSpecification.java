package me.timur.travelcompanyapp.specification;

import me.timur.travelcompanyapp.entity.Company;
import me.timur.travelcompanyapp.entity.Group;
import me.timur.travelcompanyapp.entity.User;
import me.timur.travelcompanyapp.exception.ResourceNotFoundException;
import me.timur.travelcompanyapp.security.auth.ApplicationUserRole;
import me.timur.travelcompanyapp.service.CompanyService;
import me.timur.travelcompanyapp.service.UserService;
import me.timur.travelcompanyapp.util.DateUtil;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Join;
import java.util.Optional;

/**
 * Created by Temurbek Ismoilov on 25/03/22.
 */

@Component
public record GroupSpecification(
        CompanyService companyService,
        UserService userService
) implements EntitySpecification{

    public Specification<Group> tourOperator(String tourOperatorName) {
        try {
            User tourOperator = userService.findByUsernameAndRole(tourOperatorName, ApplicationUserRole.TOUR_OPERATOR);
            return tourOperator(tourOperator);
        } catch (ResourceNotFoundException e) {
            return emptySpecification();
        }
    }

    public Specification<Group> tourOperator(User tourOperator) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("tourOperator"), tourOperator);
    }

    public Specification<Group> groupNumber(String groupNumber) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("groupNumber"), groupNumber);
    }

    public Specification<Group> fromDate(String fromDate) {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.greaterThanOrEqualTo(root.get("arrival"), DateUtil.stringToDateTimeOrNull(fromDate));
    }

    public Specification<Group> toDate(String fromDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThan(root.get("arrival"), DateUtil.stringToDateTimeOrNull(fromDate));
    }

    public Specification<Group> isActive(String isActive) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("isActive"), Boolean.valueOf(isActive));
    }

    public Specification<Group> company(String companyName) {
        Optional<Company> companyOptional = companyService.findByName(companyName);

        if (companyOptional.isEmpty())
            return emptySpecification();

        return (root, query, criteriaBuilder) -> {
            Join<Group, Company> join = root.join("company");
            return criteriaBuilder.equal(join.get("name"), companyName);
        };
    }
}
