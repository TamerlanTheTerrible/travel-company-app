package me.timur.travelcompanyapp.service;

import me.timur.travelcompanyapp.entity.Company;
import me.timur.travelcompanyapp.entity.Group;
import me.timur.travelcompanyapp.entity.User;
import me.timur.travelcompanyapp.exception.GroupAccessDeniedException;
import me.timur.travelcompanyapp.exception.ResourceNotFoundException;
import me.timur.travelcompanyapp.model.reservation.pre.GroupRegistrationRequest;
import me.timur.travelcompanyapp.repository.GroupRepository;
import me.timur.travelcompanyapp.security.jwt.JwtTokenVerifier;
import org.springframework.stereotype.Service;

/**
 * Created by Temurbek Ismoilov on 06/02/22.
 */

@Service
public record GroupDefaultService(
      GroupRepository groupRepository,
      CompanyService companyService,
      UserService userService,
      JwtTokenVerifier tokenVerifier
) implements GroupService {

    @Override
    public Integer register(GroupRegistrationRequest dto, User user) {
        final Company company = companyService.findByNameOrCreate(dto.getCompany());
        var group = new Group(dto, user, company);
        return groupRepository.save(group).getId();
    }

    @Override
    public Boolean cancel(Integer id, User user) {
        Group group = findById(id);

        if (!group.belongsToUser(user))
            throw new GroupAccessDeniedException("The user has no access to cancel the group");

        group.setIsActive(false);
        groupRepository.save(group);
        return true;
    }

    @Override
    public Group findById(Integer id) {
        return groupRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Could not find group with id " + id));
    }
}
