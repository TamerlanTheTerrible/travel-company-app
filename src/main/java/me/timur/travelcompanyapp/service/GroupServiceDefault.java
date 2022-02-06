package me.timur.travelcompanyapp.service;

import me.timur.travelcompanyapp.domain.Group;
import me.timur.travelcompanyapp.domain.User;
import me.timur.travelcompanyapp.model.group.GroupRegistrationRequest;
import me.timur.travelcompanyapp.repository.GroupRepository;
import me.timur.travelcompanyapp.security.jwt.JwtTokenVerifier;
import me.timur.travelcompanyapp.util.DateUtil;
import org.springframework.stereotype.Service;

/**
 * Created by Temurbek Ismoilov on 06/02/22.
 */

@Service
public record GroupServiceDefault(
      GroupRepository groupRepository,
      CompanyService companyService,
      UserService userService,
      JwtTokenVerifier tokenVerifier
) implements GroupService {

    @Override
    public Integer register(GroupRegistrationRequest dto, User user) {
        var group = Group.builder()
                .groupNumber(dto.getGroupNumber())
                .company(companyService.findByNameOrCreate(dto.getCompany()))
                .country(dto.getCountry())
                .tourOperator(user)
                .arrival(DateUtil.stringToDateTimeOrNull(dto.getArrival()))
                .departure(DateUtil.stringToDateTimeOrNull(dto.getDeparture()))
                .registeredSize(dto.getRegisteredSize())
                .build();

        return groupRepository.save(group).getId();
    }
}
