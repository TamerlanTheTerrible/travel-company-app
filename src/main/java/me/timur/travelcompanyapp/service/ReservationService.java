package me.timur.travelcompanyapp.service;

import me.timur.travelcompanyapp.entity.Application;
import me.timur.travelcompanyapp.model.reservation.post.Reserved;
import me.timur.travelcompanyapp.model.reservation.pre.Reservable;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Temurbek Ismoilov on 25/02/22.
 */

public interface ReservationService {

    void reserveAll(Application application, List<Reservable> bookingList);

    HashMap<Integer, List<Reserved>> getAllByApplicationList(List<Application> applications);
}
