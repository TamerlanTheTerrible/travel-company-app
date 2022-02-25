package me.timur.travelcompanyapp.domain.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.timur.travelcompanyapp.domain.BaseEntity;
import me.timur.travelcompanyapp.domain.Group;
import me.timur.travelcompanyapp.domain.User;

import javax.persistence.*;

/**
 * Created by Temurbek Ismoilov on 08/02/22.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Application extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "type")
    private ApplicationType type;

    @ManyToOne
    @JoinColumn(name = "tour_operator_id")
    private User tourOperator;

}
