package me.timur.travelcompanyapp.repository;

import me.timur.travelcompanyapp.domain.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Temurbek Ismoilov on 06/02/22.
 */

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
}
