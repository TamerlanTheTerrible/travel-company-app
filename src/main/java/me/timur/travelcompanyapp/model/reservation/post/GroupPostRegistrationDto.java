package me.timur.travelcompanyapp.model.reservation.post;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import me.timur.travelcompanyapp.entity.Group;
import me.timur.travelcompanyapp.util.DateUtil;

import javax.persistence.criteria.CriteriaBuilder;
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

    public GroupPostRegistrationDto(Group entity) {
        this.id = entity.getId();
        this.groupNumber = entity.getGroupNumber();
        this.company = entity.getCompany().getName();
        this.country = entity.getCountry();
        this.arrival = DateUtil.dateTimeToString(entity.getArrival());
        this.departure = DateUtil.dateTimeToString(entity.getDeparture());
        this.tourOperatorName = entity.getTourOperator().getUsername();
        this.tourOperatorId = entity.getTourOperator().getId();
    }

    public static List<GroupPostRegistrationDto> toDtoList(List<Group> groups) {
        return groups.stream().map(GroupPostRegistrationDto::new).collect(Collectors.toList());
    }
}
