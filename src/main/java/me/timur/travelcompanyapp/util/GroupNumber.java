package me.timur.travelcompanyapp.util;

import me.timur.travelcompanyapp.model.reservation.pre.GroupRegistrationRequest;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Created by Temurbek Ismoilov on 04/04/22.
 */

public class GroupNumber {

    public static String generate(String groupNumber, LocalDateTime arrival) {
        return groupNumber + '-' + arrival.getYear();
    }

    public static String removeYear(String groupNumberWithYear) {
        int last = groupNumberWithYear.length()-5;
        return groupNumberWithYear.substring(0, last);
    }

    public static String getYear(String groupNumberWithYear) {
        int last = groupNumberWithYear.length();
        int first = last -4;
        return groupNumberWithYear.substring(first, last);
    }

    public static String update(GroupRegistrationRequest dto, String currentGroupNumber, LocalDateTime currentArrivalDateTime ) {
        return dto.getGroupNumber() != null
                ? generate(dto.getGroupNumber(), currentArrivalDateTime)
                : currentGroupNumber;
    }
}
