package me.timur.travelcompanyapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "room")
public class Room extends BaseEntity {

    public Room(RoomType type, Integer quantity) {
        this.setRoomType(type);
        this.setQuantity(quantity);
    }

    @ManyToOne
    @JoinColumn(name = "accommodation_reservation_id")
    private AccommodationReservation accommodationReservation;

    @ManyToOne
    @JoinColumn(name = "room_type_id")
    private RoomType roomType;

    @Column(name = "quantity")
    private Integer quantity;


}