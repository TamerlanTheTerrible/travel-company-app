package me.timur.travelcompanyapp.model.reservation.post;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.timur.travelcompanyapp.entity.AccommodationReservation;
import me.timur.travelcompanyapp.entity.ReservationEntity;
import me.timur.travelcompanyapp.model.RoomDto;
import me.timur.travelcompanyapp.util.DateUtil;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 20/03/22.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AccommodationPostReservationDto implements Reserved{

    public AccommodationPostReservationDto(AccommodationReservation reservation) {
        this.accommodationReservationId = reservation.getId();
        this.accommodationId = reservation.getAccommodation().getId();
        this.accommodationName = reservation.getAccommodation().getName();
        this.checkIn = DateUtil.dateTimeToString(reservation.getCheckIn());
        this.checkOut = DateUtil.dateTimeToString(reservation.getCheckOut());
        this.rooming = RoomDto.fromEntityList(reservation.getRooming());
    }

    Integer accommodationReservationId;
    Integer accommodationId;
    String accommodationName;
    String checkIn;
    String checkOut;
    List<RoomDto> rooming;
}
