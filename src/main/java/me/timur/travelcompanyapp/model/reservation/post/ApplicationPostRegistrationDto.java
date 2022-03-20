package me.timur.travelcompanyapp.model.reservation.post;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import me.timur.travelcompanyapp.model.reservation.pre.Reservable;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 14/03/22.
 */

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ApplicationPostRegistrationDto {
    private Integer applicationId;
    private String applicationType;
    private String applicationDateCreated;
    private Integer groupId;
    private String groupNumber;
    private String groupSize;
    private List<Reservable> reservations;
}
