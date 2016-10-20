package ru.javawebinar.topjava.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.format.Formatter;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * GKislin
 * 07.01.2015.
 */
public class TimeUtil implements Formatter<LocalDateTime> {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static final LocalDate MIN_DATE = LocalDate.of(1, 1, 1);
    public static final LocalDate MAX_DATE = LocalDate.of(3000, 1, 1);

    public static <T extends Comparable<? super T>> boolean isBetween(T value, T start, T end) {
        return value.compareTo(start) >= 0 && value.compareTo(end) <= 0;
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }

    public static LocalDate parseLocalDate(String str) {
        return StringUtils.isEmpty(str) ? null : LocalDate.parse(str);
    }

    public static LocalTime parseLocalTime(String str) {
        return StringUtils.isEmpty(str) ? null : LocalTime.parse(str);
    }



    @Override
    public LocalDateTime parse(String s, Locale locale) throws ParseException {
        return LocalDateTime.parse(s);
    }

    @Override
    public String print(LocalDateTime localDateTime, Locale locale) {
        return localDateTime.format(DATE_TIME_FORMATTER);
    }
}
