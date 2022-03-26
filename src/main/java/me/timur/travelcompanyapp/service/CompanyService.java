package me.timur.travelcompanyapp.service;

import me.timur.travelcompanyapp.entity.Company;

import java.util.Optional;

/**
 * Created by Temurbek Ismoilov on 06/02/22.
 */

public interface CompanyService {
    Company findByNameOrCreate(String companyName);
    Optional<Company> findByName(String companyName);
}
