package me.timur.travelcompanyapp.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.timur.travelcompanyapp.model.reservation.AccommdationBookableDto;

/**
 * Created by Temurbek Ismoilov on 25/02/22.
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({@JsonSubTypes.Type(value = AccommdationBookableDto.class)})
@Data
@NoArgsConstructor
public abstract class Bookable {
}
