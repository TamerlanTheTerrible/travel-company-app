package me.timur.travelcompanyapp.service;

import me.timur.travelcompanyapp.entity.AccommodationReservation;
import me.timur.travelcompanyapp.entity.Application;
import me.timur.travelcompanyapp.entity.Room;
import me.timur.travelcompanyapp.model.reservation.pre.AccommodationPreReservationDto;
import me.timur.travelcompanyapp.model.reservation.pre.Reservable;
import me.timur.travelcompanyapp.repository.AccommodationBookableRepository;
import me.timur.travelcompanyapp.repository.BookingStatusRepository;
import me.timur.travelcompanyapp.util.DateUtil;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Temurbek Ismoilov on 25/02/22.
 */

@Component("accommodationBookingService")
public record AccommodationReservationService(
        AccommodationService accommService,
        RoomTypeService roomTypeService,
        AccommodationBookableRepository accommBookabaleRepo,
        BookingStatusRepository bookingStatusRepo
) implements ReservationService {

    static final String ACCOMMODATION_BOOKABLE_DEFAULT_STATUS = "REGISTERED";



    @Override
    public void reserveAll(Application application, List<Reservable> reservableList) {

        List<AccommodationReservation> accommBookables = reservableList
                .stream()
                .map(dto -> accommodationDtoToEntity((AccommodationPreReservationDto) dto, application))
                .collect(Collectors.toList());

        accommBookabaleRepo.saveAll(accommBookables);
    }

    private AccommodationReservation accommodationDtoToEntity(AccommodationPreReservationDto dto, Application application) {
        //create rooms from dto
        List<Room> rooms = roomTypeService.roomDtosToRooms(dto.getRooming());

        //create accommodationBookable and assign values
        AccommodationReservation accommodationReservation = AccommodationReservation.builder()
                .accommodation(accommService.findByName(dto.getAccommodationName()))
                .checkIn(DateUtil.stringToDateTimeOrNull(dto.getCheckIn()))
                .checkOut(DateUtil.stringToDateTimeOrNull(dto.getCheckOut()))
                .rooming(rooms)
                .status(bookingStatusRepo.findById(ACCOMMODATION_BOOKABLE_DEFAULT_STATUS).orElseThrow())
                .build();

        //assign application to the accommodationBookable
        accommodationReservation.setApplication(application);

        //set accommodationBookable for each room
        rooms.forEach(room -> room.setAccommodationReservation(accommodationReservation));

        return accommodationReservation;
    }
}

