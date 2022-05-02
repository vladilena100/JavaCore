package com.solutions.vasilieva;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class Java8Test {

    private Java8 java8;

    @Before
    public void initTest() {
        java8 = new Java8();
    }

    @After
    public void afterTest() {
        java8 = null;
    }

    @Test
    public void between() {
        LocalDate date1 = LocalDate.of(2021, Month.JANUARY, 31);
        LocalDate date2 = LocalDate.of(2021, Month.MARCH, 1);
        assertEquals("1 month 1 day", java8.between(date1, date2));
    }

    @Test
    public void mondays() {
        LocalDate[] mondays = {LocalDate.of(2021, Month.MAY, 3),
                LocalDate.of(2021, Month.MAY, 10),
                LocalDate.of(2021, Month.MAY, 17),
                LocalDate.of(2021, Month.MAY, 24),
                LocalDate.of(2021, Month.MAY, 31)};
        assertEquals(Arrays.toString(mondays), Arrays.toString(java8.mondays(new GregorianCalendar().toInstant())));
    }

    @Test
    public void isFridays13() {
        LocalDate date = LocalDate.of(2021, Month.AUGUST, 13);
        assertEquals(true, java8.isFridays13(date));
    }

    @Test
    public void formatFullJava8() {
        ZonedDateTime zonedDateTimeOf = ZonedDateTime.of(2018, 01, 22, 0, 0,
                0, 0, ZoneId.of("UTC"));
        assertEquals("2018-01-22T00:00:00Z[UTC]", java8.formatFullJava8(zonedDateTimeOf, "UA"));
    }
}