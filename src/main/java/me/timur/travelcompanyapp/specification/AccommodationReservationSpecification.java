package me.timur.travelcompanyapp.specification;

import me.timur.travelcompanyapp.entity.AccommodationReservation;
import me.timur.travelcompanyapp.entity.Application;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 22/03/22.
 */

@Component
public record AccommodationReservationSpecification() implements EntitySpecification {

    public Specification<AccommodationReservation> applicationList(List<Application> applications) {
        return (root, query, criteriaBuilder) -> root.get("application").in(applications);
    }
}
