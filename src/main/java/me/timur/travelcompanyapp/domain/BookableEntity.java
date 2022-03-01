package me.timur.travelcompanyapp.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Temurbek Ismoilov on 08/02/22.
 */

@Data
@MappedSuperclass
public abstract class BookableEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "application_id")
    private Application application;

    private String comment;
}
