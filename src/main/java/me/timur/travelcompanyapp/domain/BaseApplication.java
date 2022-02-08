package me.timur.travelcompanyapp.domain;

import lombok.Data;
//import me.timur.travelcompanyapp.model.ApplicationType;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * Created by Temurbek Ismoilov on 08/02/22.
 */

@Data
@MappedSuperclass
public class BaseApplication extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

//    private ApplicationType type;
}
