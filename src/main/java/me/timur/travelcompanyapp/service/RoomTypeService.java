package me.timur.travelcompanyapp.service;

import me.timur.travelcompanyapp.domain.Room;
import me.timur.travelcompanyapp.domain.RoomType;
import me.timur.travelcompanyapp.exception.ResourceNotFoundException;
import me.timur.travelcompanyapp.model.RoomDto;
import me.timur.travelcompanyapp.repository.RoomTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Temurbek Ismoilov on 26/02/22.
 */

@Service
public record RoomTypeService(
        RoomTypeRepository roomTypeRepository
) {

    public RoomType findByName(String name) {
        return roomTypeRepository
                .findById(name)
                .orElseThrow(() -> new ResourceNotFoundException("Could not find RoomType by name " + name));
    }

    public List<Room> roomDtosToRooms(List<RoomDto> dtos) {
        return dtos
                .stream()
                .map(roomDto -> new Room(findByName(roomDto.getType()), roomDto.getQuantity()))
                .collect(Collectors.toList());
    }
}
