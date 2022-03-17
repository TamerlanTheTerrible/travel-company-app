package me.timur.travelcompanyapp.util;

import me.timur.travelcompanyapp.exception.InvalidInputException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Created by Temurbek Ismoilov on 06/02/22.
 */

public class DateUtil {
    private final static String dateTimePattern;
    private final static String datePattern;
    private final static DateTimeFormatter formatter;

    static {
        dateTimePattern = "yyyy-MM-dd HH:mm";
        datePattern = "yyyy-MM-dd";
        formatter = DateTimeFormatter.ofPattern(dateTimePattern);
    }

    public static LocalDateTime stringToDateTimeOrNull(String dateInString) {
        if (dateInString != null){
            try {
                return LocalDateTime.parse(addTimeIfAbsent(dateInString), formatter);
            } catch (DateTimeParseException e){
                throw new InvalidInputException(String.format("Required pattern %s: ", dateTimePattern), e);
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

    public static String addTimeIfAbsent(String rawDateTime) {
        if (rawDateTime.length() == datePattern.length())
            return rawDateTime + " 00:00";
        else if (rawDateTime.length() == dateTimePattern.length())
            return rawDateTime;
        else
            throw new InvalidInputException(String.format("Required patterns: %s, %s", dateTimePattern, datePattern));
    }

    public static String formatDateInString(String dateInString) {
        final LocalDateTime date = stringToDateTimeOrNull(dateInString);
        return dateTimeToString(date);
    }

}
