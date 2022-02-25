package me.timur.travelcompanyapp.domain.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.timur.travelcompanyapp.domain.Accommodation;
import me.timur.travelcompanyapp.domain.Room;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Temurbek Ismoilov on 08/02/22.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class AccommodationApplication extends BookableEntity {

    @ManyToOne
    @JoinColumn(name = "accommodation_ID")
    private Accommodation accommodation;

    @Column(name = "check_in")
    private LocalDateTime checkIn;

    @Column(name = "check_out")
    private LocalDateTime checkOut;

    @OneToMany(mappedBy = "application", fetch = FetchType.EAGER)
    private List<Room> rooming;
}
