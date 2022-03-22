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
import java.util.HashMap;
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
    private String applicationType;
    private Lang applicationTypeLang;
    private Integer groupId;
    private String groupNumber;
    private Short groupSize;
    private List<Reserved> reservations;

    public ApplicationPostRegistrationDto(Application application, List<Reserved> reservations) {
        this.applicationId = application.getId();
        this.applicationType = application.getType().getName();
        this.applicationTypeLang = application.getType().getLang();
        this.groupId = application.getGroup().getId();
        this.groupNumber = application.getGroup().getGroupNumber();
        this.groupSize = application.getGroup().getRegisteredSize();
        this.reservations = reservations;
    }

    public static List<ApplicationPostRegistrationDto> fromEntityList(List<Application> applications, HashMap<Integer, List<Reserved>> reservationDtoList) {
        return applications
                .stream()
                .map(application -> new ApplicationPostRegistrationDto(application, reservationDtoList.get(application.getId())))
                .collect(Collectors.toList());
    }
}
