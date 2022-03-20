package me.timur.travelcompanyapp.repository;

import me.timur.travelcompanyapp.entity.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Temurbek Ismoilov on 26/02/22.
 */

public interface AccommodationRepository extends JpaRepository<Accommodation, Integer> {

    Optional<Accommodation> findByName(String name);
}
