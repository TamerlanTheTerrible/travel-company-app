package me.timur.travelcompanyapp.model.reservation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.timur.travelcompanyapp.model.Bookable;
import me.timur.travelcompanyapp.model.RoomDto;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 25/02/22.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AccommdationBookableDto extends Bookable {
    String accommodationName;
    String checkIn;
    String checkOut;
    List<RoomDto> rooming;
}
