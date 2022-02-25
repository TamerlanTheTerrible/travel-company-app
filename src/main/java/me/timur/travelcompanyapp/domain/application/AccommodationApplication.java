package me.timur.travelcompanyapp.domain.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.timur.travelcompanyapp.domain.Accommodation;
import me.timur.travelcompanyapp.domain.AccommodationCategory;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Temurbek Ismoilov on 08/02/22.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class AccommodationApplication extends Bookable {

    @ManyToOne
    @JoinColumn(name = "accommodation_ID")
    private Accommodation accommodation;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private AccommodationCategory category;

    @Column(name = "check_in")
    private LocalDateTime checkIn;

    @Column(name = "check_out")
    private LocalDateTime checkOut;
}
