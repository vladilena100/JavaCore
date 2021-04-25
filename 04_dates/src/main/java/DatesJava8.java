import com.nixsolutions.ppp.dates.DateUtilJava8;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DatesJava8 implements DateUtilJava8 {
    @Override
    public String between(LocalDate date1, LocalDate date2) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        //LocalDate startDate = LocalDate.parse(date1, formatter);
        //LocalDate endDate = LocalDate.parse("05.03.2013", formatter);
        Period period = Period.between(date1, date2);
        int year = period.getYears();
        int month = period.getMonths();
        int day = period.getDays();

        return;
    }

    @Override
    public LocalDate[] mondays(Instant instant) {
        return new LocalDate[0];
    }

    @Override
    public boolean isFridays13(LocalDate date) {
        return false;
    }

    @Override
    public String formatFullJava8(ZonedDateTime date, String language) {
        return null;
    }
}
