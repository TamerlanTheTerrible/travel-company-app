package me.timur.travelcompanyapp.repository;

import me.timur.travelcompanyapp.domain.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Temurbek Ismoilov on 26/02/22.
 */

public interface RoomTypeRepository extends JpaRepository<RoomType, String> {
}
