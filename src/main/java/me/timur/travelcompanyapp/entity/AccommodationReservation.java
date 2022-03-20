package me.timur.travelcompanyapp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Temurbek Ismoilov on 08/02/22.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "accommodation_reservation")
public class AccommodationReservation extends ReservationEntity {

    @ManyToOne
    @JoinColumn(name = "accommodation_ID")
    private Accommodation accommodation;

    @Column(name = "check_in")
    private LocalDateTime checkIn;

    @Column(name = "check_out")
    private LocalDateTime checkOut;

    @ManyToOne
    @JoinColumn(name = "reservation_status")
    private ReservationStatus status;

    @OneToMany(mappedBy = "accommodationReservation", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Room> rooming;
}
