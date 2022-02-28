package me.timur.travelcompanyapp.service;

import me.timur.travelcompanyapp.domain.AccommodationBookable;
import me.timur.travelcompanyapp.domain.Application;
import me.timur.travelcompanyapp.model.AccommdationBookableDto;
import me.timur.travelcompanyapp.model.Bookable;
import me.timur.travelcompanyapp.repository.AccommodationBookableRepository;
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
        AccommodationBookableRepository accommBookabaleRepo
) implements BookingService {

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
                .checkIn(dto.getCheckIn())
                .checkOut(dto.getCheckOut())
                .rooming(roomTypeService.roomDtosToRooms(dto.getRooming()))
                .build();

        accommodationBookable.setApplication(application);
        return accommodationBookable;
    }
}
