package me.timur.travelcompanyapp.service;

import me.timur.travelcompanyapp.entity.Company;
import me.timur.travelcompanyapp.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Temurbek Ismoilov on 06/02/22.
 */

@Service
public record CompanyDefaultService(
        CompanyRepository companyRepository
) implements CompanyService {

    @Override
    public Company findByNameOrCreate(String name) {
        Optional<Company> companyOptional = companyRepository.findCompanyByName(name);
        return companyOptional.orElseGet(() -> registerCompany(name));
    }

    private Company registerCompany(String name) {
        return companyRepository.save(new Company(name));
    }
}
