package me.timur.travelcompanyapp.model.reservation.post;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import me.timur.travelcompanyapp.entity.Group;
import me.timur.travelcompanyapp.util.DateUtil;
import me.timur.travelcompanyapp.util.GroupNumber;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Temurbek Ismoilov on 26/03/22.
 */

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GroupPostRegistrationDto {
    Integer id;
    String groupNumber;
    String company;
    String country;
    String arrival;
    String departure;
    Short registeredSize;
    String tourOperatorName;
    Integer tourOperatorId;

    public static GroupPostRegistrationDto fromEntity(Group entity) {
        GroupPostRegistrationDto dto = new GroupPostRegistrationDto();
        dto.id = entity.getId();
        dto.groupNumber = GroupNumber.removeYear(entity.getGroupNumber());
        dto.company = entity.getCompany().getName();
        dto.country = entity.getCountry();
        dto.arrival = DateUtil.dateTimeToString(entity.getArrival());
        dto.departure = DateUtil.dateTimeToString(entity.getDeparture());
        dto.registeredSize = entity.getRegisteredSize();
        dto.tourOperatorName = entity.getTourOperator().getUsername();
        dto.tourOperatorId = entity.getTourOperator().getId();
        return dto;
    }

    public static List<GroupPostRegistrationDto> toDtoList(List<Group> groups) {
        return groups.stream()
                .sorted(Comparator.comparing(Group::getArrival))
                .map(GroupPostRegistrationDto::fromEntity)
                .collect(Collectors.toList());
    }
}
