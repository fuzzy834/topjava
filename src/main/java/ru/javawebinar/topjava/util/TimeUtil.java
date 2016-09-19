package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.to.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * GKislin
 * 07.01.2015.
 */
public class TimeUtil {
    public static final DateTimeFormatter DATE_TME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static boolean isBetweenTime(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) <= 0;
    }

    public static boolean isBetweenDate(LocalDate lt, LocalDate startDate, LocalDate endDate) {
        return lt.compareTo(startDate) >= 0 && lt.compareTo(endDate) <= 0;
    }

    public static List<MealWithExceed> filterByDateTime(List<MealWithExceed> lt, LocalDate sd, LocalDate ed, LocalTime st, LocalTime et){
        return lt.stream().filter(mealWithExceed ->
            isBetweenTime(mealWithExceed.getDateTime().toLocalTime(), st, et)
                    &&isBetweenDate(mealWithExceed.getDateTime().toLocalDate(), sd, ed))
                .collect(Collectors.toList());
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TME_FORMATTER);
    }
}
