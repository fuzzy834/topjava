package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * GKislin
 * 07.01.2015.
 */
public class TimeUtil {

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);


    public static boolean isBetween(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) <= 0;
    }

    public static String timeToString(LocalTime localTime) {
        return localTime == null ? "" : localTime.format(TIME_FORMATTER);
    }

    public static String dateToString(LocalDate localDate) {
        return localDate == null ? "" : localDate.format(DATE_FORMATTER);
    }
}
