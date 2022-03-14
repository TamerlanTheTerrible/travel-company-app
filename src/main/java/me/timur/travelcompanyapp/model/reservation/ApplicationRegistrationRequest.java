package me.timur.travelcompanyapp.model.reservation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import me.timur.travelcompanyapp.model.Bookable;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 25/02/22.
 */

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ApplicationRegistrationRequest {
    private Integer groupId;
    private String applicationType;
    private String tourOperatorName;
    private List<Bookable> bookingList;
}