import com.nixsolutions.ppp.dates.DateUtilJava8;

import java.time.*;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOG = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        DateUtilJava8 c = new DatesJava8();
        DatesJava7 dj = new DatesJava7();
        Calendar date = new GregorianCalendar(2021, Calendar.AUGUST, 13);
        Date date01 = new Date(2021, Calendar.AUGUST, 13);
        ZonedDateTime zonedDateTimeOf = ZonedDateTime.of(2018, 01, 22, 0, 0,
                0, 0, ZoneId.of("UTC"));
        LocalDate date1 = LocalDate.of(2021, Month.JANUARY, 31);
        LocalDate date2 = LocalDate.of(2021, Month.MARCH, 1);
        //Date date02 = new Date(2020, Calendar.MARCH, 1);
        //LOG.info(c.between(LocalDate.of(2019, 6, 15),
        //LocalDate.of(2021, 2, 1)));
        //LOG.info("between" + dj.between(date01, date02));
        //LOG.info("days In Month " + Arrays.toString(dj.daysInMonth(2021)));
        //LOG.info("mondays " + Arrays.toString(dj.mondays(2, 2021)));
        //LOG.info("fridays 13th " + dj.isFridays13(new GregorianCalendar(2021,
        //Calendar.AUGUST, 10).getTime()));
        //LOG.info("format " + dj.formatFull(new GregorianCalendar(2021,
        //Calendar.AUGUST, 10).getTime(), "US"));
        //LOG.info("format 8 " + c.formatFullJava8(zonedDateTimeOf, "UA"));
        //LOG.info("between " + c.between(date1, date2));
        LOG.info("Mondays" + Arrays.toString(c.mondays(new GregorianCalendar().toInstant())));
    }
}
