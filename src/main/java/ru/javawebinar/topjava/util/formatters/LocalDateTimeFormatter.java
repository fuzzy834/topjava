package ru.javawebinar.topjava.util.formatters;

import org.springframework.format.Formatter;
import ru.javawebinar.topjava.util.TimeUtil;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Locale;

/**
 * Created by Vitalii on 10/30/2016.
 */
public class LocalDateTimeFormatter implements Formatter<LocalDateTime> {

    @Override
    public LocalDateTime parse(String s, Locale locale) throws ParseException {
        return LocalDateTime.parse(s);
    }

    @Override
    public String print(LocalDateTime dateTime, Locale locale) {
        return dateTime.format(TimeUtil.DATE_TIME_FORMATTER);
    }
}
