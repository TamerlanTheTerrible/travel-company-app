package me.timur.travelcompanyapp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.timur.travelcompanyapp.model.reservation.pre.GroupRegistrationRequest;
import me.timur.travelcompanyapp.util.DateUtil;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Created by Temurbek Ismoilov on 06/02/22.
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "groups")
public class Group extends BaseEntity {

    private String groupNumber;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    private String country;

    @ManyToOne
    @JoinColumn(name = "tour_operator_id")
    private User tourOperator;

    private LocalDateTime arrival;

    private LocalDateTime departure;

    private Short registeredSize = 1;

    private Boolean isActive = true;

    public static Group create (GroupRegistrationRequest dto, User user, Company company) {
        Group group = new Group();

        group.groupNumber = dto.getGroupNumber();
        group.company = company;
        group.country = dto.getCountry();
        group.tourOperator = user;
        group.arrival = DateUtil.stringToDateTimeOrNull(dto.getArrival());
        group.departure = DateUtil.stringToDateTimeOrNull(dto.getDeparture());
        group.registeredSize = dto.getRegisteredSize();
        group.isActive = true;

        return group;
    }

    public Group update(GroupRegistrationRequest dto) {
        this.groupNumber = Objects.requireNonNullElse(dto.getGroupNumber(), this.groupNumber);
        this.country = Objects.requireNonNullElse(dto.getCountry(), this.country);
        this.registeredSize = Objects.requireNonNullElse(dto.getRegisteredSize(), this.registeredSize);
        this.arrival = Objects.requireNonNullElse(
                DateUtil.stringToDateTimeOrNull(dto.getArrival()),
                this.arrival);
        this.departure = Objects.requireNonNullElse(
                DateUtil.stringToDateTimeOrNull(dto.getDeparture()),
                this.departure);

        return this;
    }

    public Boolean belongsToUser(User user) {
        return Objects.equals(this.tourOperator.getId(), user.getId());
    }
}
