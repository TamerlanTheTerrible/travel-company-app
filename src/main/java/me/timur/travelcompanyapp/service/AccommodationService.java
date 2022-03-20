package me.timur.travelcompanyapp.service;

import me.timur.travelcompanyapp.entity.Accommodation;
import me.timur.travelcompanyapp.exception.ResourceNotFoundException;
import me.timur.travelcompanyapp.repository.AccommodationRepository;
import org.springframework.stereotype.Service;

/**
 * Created by Temurbek Ismoilov on 26/02/22.
 */

@Service
public record AccommodationService(
        AccommodationRepository accommodationRepository
){
    public Accommodation findByName(String name){
        return accommodationRepository
                .findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find accommodation by name " + name));
    }

}
