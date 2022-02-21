package me.timur.travelcompanyapp.domain.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.timur.travelcompanyapp.domain.Accommodation;
import me.timur.travelcompanyapp.domain.BaseEntity;
import me.timur.travelcompanyapp.model.Bookable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Temurbek Ismoilov on 08/02/22.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class AccommodationApplication extends BaseEntity implements Bookable {

    @ManyToOne
    @JoinColumn(name = "application_id")
    public Application application;

    @ManyToOne
    @JoinColumn(name = "accommodation_ID")
    private Accommodation accommodation;

}
