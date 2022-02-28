package me.timur.travelcompanyapp.domain;

import lombok.Data;
import me.timur.travelcompanyapp.model.BookingStatus;
import org.springframework.security.core.Transient;

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

    @ManyToOne
    @JoinColumn(name = "booking_status")
    private BookingStatus status;

    private String comment;
}
