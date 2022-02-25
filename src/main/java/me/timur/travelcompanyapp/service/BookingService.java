package me.timur.travelcompanyapp.service;

import me.timur.travelcompanyapp.domain.application.Application;
import me.timur.travelcompanyapp.model.Bookable;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 25/02/22.
 */

public interface BookingService {
    Application book(List<Bookable> bookingList);
}
