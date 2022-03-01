package me.timur.travelcompanyapp.service;

import me.timur.travelcompanyapp.domain.AccommodationBookable;
import me.timur.travelcompanyapp.domain.Application;
import me.timur.travelcompanyapp.model.AccommdationBookableDto;
import me.timur.travelcompanyapp.model.Bookable;
import me.timur.travelcompanyapp.repository.AccommodationBookableRepository;
import me.timur.travelcompanyapp.repository.BookingStatusRepository;
import me.timur.travelcompanyapp.util.DateUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Temurbek Ismoilov on 25/02/22.
 */

@Component("accommodationBookingService")
public record AccommodationBookingService(
        AccommodationService accommService,
        RoomTypeService roomTypeService,
        AccommodationBookableRepository accommBookabaleRepo,
        BookingStatusRepository bookingStatusRepo
) implements BookingService {

    static final String ACCOMMODATION_BOOKABLE_DEFAULT_STATUS = "REGISTERED";

    @Override
    public void bookAll(Application application, List<Bookable> bookableList) {

        List<AccommodationBookable> accommBookables = bookableList
                .stream()
                .map(dto -> accommodationDtoToEntity((AccommdationBookableDto) dto, application))
                .collect(Collectors.toList());

        accommBookabaleRepo.saveAll(accommBookables);
    }

    private AccommodationBookable accommodationDtoToEntity(AccommdationBookableDto dto, Application application) {
        AccommodationBookable accommodationBookable = AccommodationBookable.builder()
                .accommodation(accommService.findByName(dto.getAccommodationName()))
                .checkIn(DateUtil.stringToDateTimeOrNull(dto.getCheckIn()))
                .checkOut(DateUtil.stringToDateTimeOrNull(dto.getCheckOut()))
                .rooming(roomTypeService.roomDtosToRooms(dto.getRooming()))
                .status(bookingStatusRepo.findById(ACCOMMODATION_BOOKABLE_DEFAULT_STATUS).orElseThrow())
                .build();

        accommodationBookable.setApplication(application);
        return accommodationBookable;
    }
}
