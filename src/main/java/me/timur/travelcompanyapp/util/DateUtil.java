package me.timur.travelcompanyapp.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Temurbek Ismoilov on 06/02/22.
 */

public class DateUtil {
    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static LocalDateTime stringToDateTimeOrNull(String dateInString) {
        if (dateInString != null)
            return LocalDateTime.parse(dateInString, formatter);
        else
            return null;
    }
}
