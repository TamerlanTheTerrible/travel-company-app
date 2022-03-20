package me.timur.travelcompanyapp.model.reservation.pre;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by Temurbek Ismoilov on 25/02/22.
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
        @JsonSubTypes.Type(value = AccommodationPreReservationDto.class)
})
public interface Reservable {
}
