package com.fcamara.parking.api.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@UtilityClass
@Slf4j
public class DateUtil {
    public static String formatDate(LocalDateTime date, String pattern) {
        try {
            String formattedDateString = null;

            DateTimeFormatter dateTimeformatter = DateTimeFormatter.ofPattern(pattern);
            formattedDateString = date.format(dateTimeformatter);

            return formattedDateString;
        } catch (DateTimeParseException e) {
            return date.toString();
        }
    }

    public static LocalDateTime convertToLocalDateTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        return LocalDateTime.parse(date, formatter);
    }
}