package me.timur.travelcompanyapp.repository;

import me.timur.travelcompanyapp.domain.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Temurbek Ismoilov on 26/02/22.
 */

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
}
