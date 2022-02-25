package me.timur.travelcompanyapp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    private Short registeredSize;

    @ManyToOne
    @JoinColumn(name = "group_status")
    private GroupStatus status;
}
