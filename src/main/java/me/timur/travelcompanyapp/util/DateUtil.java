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
        if (dateInString != null){
            try {
                return LocalDateTime.parse(dateInString, formatter);
            } catch (DateTimeParseException e){
                throw new InvalidInputException(String.format("Required pattern %s: ", pattern), e);
            }
        }
        else
            return null;
    }

    public static String dateTimeToString(LocalDateTime date) {
        if (date != null){
            try {
                return date.format(formatter);
            } catch (DateTimeParseException e){
                throw new InvalidInputException("Error while formatting " + date + "to " + formatter, e);
            }
        }
        else
            return null;
    }

    public static String formatDateInString(String dateInString) {
        final LocalDateTime date = stringToDateTimeOrNull(dateInString);
        return dateTimeToString(date);
    }

}
