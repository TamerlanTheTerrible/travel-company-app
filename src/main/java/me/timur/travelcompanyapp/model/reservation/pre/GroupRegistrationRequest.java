package me.timur.travelcompanyapp.model.reservation.pre;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import me.timur.travelcompanyapp.util.GroupNumber;

/**
 * Created by Temurbek Ismoilov on 06/02/22.
 */

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GroupRegistrationRequest {
    String groupNumber;
    String company;
    String country;
    String arrival;
    String departure;
    Short registeredSize;
}

