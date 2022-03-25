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

    public Group (GroupRegistrationRequest dto, User user, Company company) {
        this.groupNumber = dto.getGroupNumber();
        this.company = company;
        this.country = dto.getCountry();
        this.tourOperator = user;
        this.arrival = DateUtil.stringToDateTimeOrNull(dto.getArrival());
        this.departure = DateUtil.stringToDateTimeOrNull(dto.getDeparture());
        this.registeredSize = dto.getRegisteredSize();
        this.isActive = true;
    }

    public Boolean belongsToUser(User user) {
        return Objects.equals(this.tourOperator.getId(), user.getId());
    }
}
