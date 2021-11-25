package validate;

import lombok.extern.log4j.Log4j2;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Log4j2
public class CheckDay {

    public static final String PATTERN_TIME = "HH:mm:ss.SSS";
    private static final String LAST_TIME_OF_DAY = "23:59:59.999";

    public long calculateTheRemainingTimeOfTheDay() {
        log.info("Start check  calculateTheRemainingTimeOfTheDay");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(PATTERN_TIME);

        LocalTime lastTimeDay = LocalTime.parse(LAST_TIME_OF_DAY, dateTimeFormatter);
        LocalTime timeConverter = LocalTime.parse(LocalTime.now().format(dateTimeFormatter));

        long timeRemaining = Duration.between(timeConverter, lastTimeDay).toMillis();

        log.info("Return " + timeRemaining);
        return timeRemaining;
    }


}
