package me.timur.travelcompanyapp.service;

import me.timur.travelcompanyapp.entity.Company;
import me.timur.travelcompanyapp.entity.Group;
import me.timur.travelcompanyapp.entity.User;
import me.timur.travelcompanyapp.exception.GroupAccessDeniedException;
import me.timur.travelcompanyapp.exception.ResourceNotFoundException;
import me.timur.travelcompanyapp.model.reservation.pre.GroupRegistrationRequest;
import me.timur.travelcompanyapp.repository.GroupRepository;
import me.timur.travelcompanyapp.specification.GroupSpecification;
import me.timur.travelcompanyapp.specification.SpecificationBuilder;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Temurbek Ismoilov on 06/02/22.
 */

@Service
public record GroupDefaultService(
        GroupRepository groupRepository,
        CompanyService companyService,
        GroupSpecification groupSpecification
) implements GroupService {

    @Override
    public Integer register(GroupRegistrationRequest dto, User user) {
        final Company company = companyService.findByNameOrCreate(dto.getCompany());
        var group = new Group(dto, user, company);
        return groupRepository.save(group).getId();
    }

    @Override
    public void cancel(Integer id, User user) {
        Group group = findById(id);

        if (!group.belongsToUser(user))
            throw new GroupAccessDeniedException("The user has no access to cancel the group");

        group.setIsActive(false);
        groupRepository.save(group);
    }

    @Override
    public Group findById(Integer id) {
        return groupRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Could not find group with id " + id));
    }

    @Override
    public List<Group> findAll(HashMap<String, String> filters) {
        return groupRepository.findAll(SpecificationBuilder.build(groupSpecification, filters));
    }
}
