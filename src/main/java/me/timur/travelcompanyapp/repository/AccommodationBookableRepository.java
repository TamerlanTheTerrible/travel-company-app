package me.timur.travelcompanyapp.repository;

import me.timur.travelcompanyapp.domain.AccommodationBookable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Temurbek Ismoilov on 26/02/22.
 */

public interface AccommodationBookableRepository extends JpaRepository<AccommodationBookable, Integer> {

}
