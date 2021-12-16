package com.example.producer.utils;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

@Log4j2
public class DateUtil {
    public static final String PATTERN_TIME = "HH:mm:ss.SSS";
    private static final String LAST_TIME_OF_DAY = "23:59:59.999";


    public static long calculateTheRemainingTimeOfTheDay() {
        log.info("Start calculateTheRemainingTimeOfTheDay");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_TIME);

        LocalTime lastTimeOfDay = LocalTime.parse(LAST_TIME_OF_DAY, formatter);
        LocalTime timeConverter = LocalTime.parse(LocalTime.now().format(formatter));

        long timeRemaining = Duration.between(timeConverter, lastTimeOfDay).toMillis();

        log.info("Return " + timeRemaining);
        return timeRemaining;
    }

    public static boolean checkFormat(String dateStr, String dateFormat) {
        DateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

}
