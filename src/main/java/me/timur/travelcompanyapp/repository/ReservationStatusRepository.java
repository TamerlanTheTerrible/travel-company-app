package me.timur.travelcompanyapp.repository;

import me.timur.travelcompanyapp.entity.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Temurbek Ismoilov on 01/03/22.
 */

@Repository
public interface ReservationStatusRepository extends JpaRepository<ReservationStatus, String> {
}
