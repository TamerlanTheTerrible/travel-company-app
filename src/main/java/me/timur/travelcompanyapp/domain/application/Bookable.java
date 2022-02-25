package me.timur.travelcompanyapp.domain.application;

import lombok.Data;
import me.timur.travelcompanyapp.domain.BaseEntity;
import me.timur.travelcompanyapp.model.BookingStatus;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * Created by Temurbek Ismoilov on 08/02/22.
 */

@Data
@MappedSuperclass
public abstract class Bookable extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "application_id")
    private Application application;

    @ManyToOne
    @JoinColumn(name = "booking_status")
    private BookingStatus status;

    private String comment;
}
