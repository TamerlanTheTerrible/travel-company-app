package me.timur.travelcompanyapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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
}
