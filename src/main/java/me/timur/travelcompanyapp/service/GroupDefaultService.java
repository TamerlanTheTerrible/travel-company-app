package me.timur.travelcompanyapp.service;

import me.timur.travelcompanyapp.entity.Company;
import me.timur.travelcompanyapp.entity.Group;
import me.timur.travelcompanyapp.entity.User;
import me.timur.travelcompanyapp.exception.ResourceAccessDeniedException;
import me.timur.travelcompanyapp.exception.ResourceNotFoundException;
import me.timur.travelcompanyapp.model.reservation.pre.GroupRegistrationRequest;
import me.timur.travelcompanyapp.repository.GroupRepository;
import me.timur.travelcompanyapp.specification.GroupSpecification;
import me.timur.travelcompanyapp.specification.SpecificationBuilder;
import org.springframework.stereotype.Service;

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
    public Group register(GroupRegistrationRequest dto, User user) {
        final Company company = companyService.findByNameOrCreate(dto.getCompany());
        var group = Group.create(dto, user, company);
        return groupRepository.save(group);
    }

    @Override
    public void cancel(Integer id, User user) {
        Group group = findByIdAndUser(id, user);

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

    @Override
    public Group update(Integer groupId, GroupRegistrationRequest groupRegistrationRequest, User tourOperator) {
        Group group = findByIdAndUser(groupId, tourOperator);
        Group groupUpdated = group.update(groupRegistrationRequest);
        return groupRepository.save(groupUpdated);
    }

    private Group findByIdAndUser(Integer id, User tourOperator) {
        Group group = findById(id);

        if (!group.belongsToUser(tourOperator))
            throw new ResourceAccessDeniedException("The user has no access to cancel the group");

        return group;
    }
}
