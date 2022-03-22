package me.timur.travelcompanyapp.repository;

import me.timur.travelcompanyapp.entity.AccommodationReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Temurbek Ismoilov on 26/02/22.
 */

public interface AccommodationReservationRepository extends JpaRepository<AccommodationReservation, Integer>, JpaSpecificationExecutor<AccommodationReservation> {

}
