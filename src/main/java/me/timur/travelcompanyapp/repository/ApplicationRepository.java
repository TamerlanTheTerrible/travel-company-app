package me.timur.travelcompanyapp.repository;

import me.timur.travelcompanyapp.domain.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 26/02/22.
 */

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer>, JpaSpecificationExecutor<Application> {

}
