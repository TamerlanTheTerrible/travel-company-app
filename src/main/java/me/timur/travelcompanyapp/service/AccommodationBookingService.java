package me.timur.travelcompanyapp.service;

import me.timur.travelcompanyapp.domain.application.Application;
import me.timur.travelcompanyapp.model.AccommodationApplicationDto;
import me.timur.travelcompanyapp.model.ApplicationCreateRequest;
import me.timur.travelcompanyapp.model.Bookable;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 25/02/22.
 */

@Qualifier("AccommodationBookingService")
public class AccommodationBookingService implements BookingService {

    @Override
    public Application book(List<Bookable> bookableList) {

    }
}
