package me.timur.travelcompanyapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.timur.travelcompanyapp.entity.Room;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Temurbek Ismoilov on 25/02/22.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RoomDto {
    String type;
    Integer quantity;

    public static List<RoomDto> fromEntityList(List<Room> rooming) {
        return rooming
                .stream()
                .map(room ->  new RoomDto(room.getRoomType().getName(), room.getQuantity()))
                .collect(Collectors.toList());
    }
}
