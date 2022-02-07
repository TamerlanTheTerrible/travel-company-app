package me.timur.travelcompanyapp.util;

import me.timur.travelcompanyapp.exception.BaseException;
import me.timur.travelcompanyapp.exception.InvalidInputException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Created by Temurbek Ismoilov on 06/02/22.
 */

public class DateUtil {
    private final static String pattern;
    private final static DateTimeFormatter formatter;

    static {
        pattern = "yyyy-MM-dd HH:mm";
        formatter = DateTimeFormatter.ofPattern(pattern);
    }

    public static LocalDateTime stringToDateTimeOrNull(String dateInString) {
        if (dateInString != null)
            return tryStringToLocalDateTime(dateInString);
        else
            return null;
    }

    private static LocalDateTime tryStringToLocalDateTime(String dateInString) {
        try {
            return LocalDateTime.parse(dateInString, formatter);
        } catch (DateTimeParseException e){
            throw new InvalidInputException(String.format("Required pattern %s: ", pattern), e);
        }
    }
}
