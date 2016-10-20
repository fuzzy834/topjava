package ru.javawebinar.topjava.util.formatters;

import org.springframework.format.Formatter;
import ru.javawebinar.topjava.util.TimeUtil;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;

/**
 * Created by Vitalii on 10/20/2016.
 */
public class LocalDateFormatter implements Formatter<LocalDate> {

    @Override
    public LocalDate parse(String s, Locale locale) throws ParseException {
        return LocalDate.parse(s);
    }

    @Override
    public String print(LocalDate localDate, Locale locale) {
        return localDate.format(TimeUtil.DATE_FORMATTER);
    }
}
