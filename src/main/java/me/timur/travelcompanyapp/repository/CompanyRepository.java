package me.timur.travelcompanyapp.repository;

import me.timur.travelcompanyapp.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Temurbek Ismoilov on 06/02/22.
 */

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    Optional<Company> findCompanyByName(String name);
}
