package ru.javawebinar.topjava.util.formatters;

import org.springframework.format.Formatter;
import ru.javawebinar.topjava.util.TimeUtil;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;

/**
 * Created by Vitalii on 10/20/2016.
 */
public class LocalTimeFormatter implements Formatter<LocalTime> {

    @Override
    public LocalTime parse(String s, Locale locale) throws ParseException {
        return LocalTime.parse(s);
    }

    @Override
    public String print(LocalTime localTime, Locale locale) {
        return localTime.format(TimeUtil.TIME_FORMATTER);
    }
}
