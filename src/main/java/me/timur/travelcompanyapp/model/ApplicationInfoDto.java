package me.timur.travelcompanyapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Temurbek Ismoilov on 14/03/22.
 */

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ApplicationInfoDto {
    private Integer groupId;
    private String groupNumber;
    private String registrationDate;
    private String groupSize;
    private String applicationType;
}
