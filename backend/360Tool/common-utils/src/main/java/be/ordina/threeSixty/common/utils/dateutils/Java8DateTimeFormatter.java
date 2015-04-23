package be.ordina.threeSixty.common.utils.dateutils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by stevedezitter on 15/04/15.
 */
@Component
public class Java8DateTimeFormatter implements DateTimeFormatterUtil<LocalDate> {

    @Value("${utils.dateformat}")
    private String dateFormat;

    private DateTimeFormatter dateFormatter;

    @PostConstruct
    public void initialize() {
        dateFormatter = DateTimeFormatter.ofPattern(dateFormat);
    }

    @Override
    public String formatDate(LocalDate localDate) {
        return localDate.format(dateFormatter);
    }

    @Override
    public LocalDate fromString(String date) {
        return LocalDate.parse(date, dateFormatter);
    }
}
