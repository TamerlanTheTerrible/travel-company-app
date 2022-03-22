package me.timur.travelcompanyapp.model.reservation.post;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import me.timur.travelcompanyapp.entity.Application;
import me.timur.travelcompanyapp.entity.ReservationEntity;
import me.timur.travelcompanyapp.model.Lang;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Temurbek Ismoilov on 14/03/22.
 */

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ApplicationPostRegistrationDto {
    private Integer applicationId;
    private Lang applicationType;;
    private Integer groupId;
    private String groupNumber;
    private Short groupSize;
    private List<Reserved> reservations;

    public ApplicationPostRegistrationDto(Application application, List<ReservationEntity> reservationEntityList) {
        this.applicationId = application.getId();
        this.applicationType = application.getType().getLang();
        this.groupId = application.getGroup().getId();
        this.groupNumber = application.getGroup().getGroupNumber();
        this.groupSize = application.getGroup().getRegisteredSize();
        this.reservations = reservationEntityList.stream().map(reservationEntity -> {
            try {
                final String className = StringUtils.capitalize(application.getType().getName().toLowerCase()) + "PostReservationDto";
                return (Reserved) Class.forName(className)
                        .getConstructor(ReservationEntity.class)
                        .newInstance(reservationEntity);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }).collect(Collectors.toList());
    }
}
