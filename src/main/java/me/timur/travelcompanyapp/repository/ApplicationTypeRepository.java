package me.timur.travelcompanyapp.repository;

import me.timur.travelcompanyapp.domain.application.ApplicationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Temurbek Ismoilov on 08/02/22.
 */

@Repository
public interface ApplicationTypeRepository extends JpaRepository<ApplicationType, String> {
}
