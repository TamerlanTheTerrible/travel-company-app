package me.timur.travelcompanyapp.service;

import me.timur.travelcompanyapp.domain.Company;

/**
 * Created by Temurbek Ismoilov on 06/02/22.
 */

public interface CompanyService {
    Company findByNameOrCreate(String company);
}