package com.solutions.vasilieva;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class Java7Test {

    private Java7 java7;

    @Before
    public void initTest() {
        java7 = new Java7();
    }

    @After
    public void afterTest() {
        java7 = null;
    }

    @Test
    public void between() {
        Date date01 = new Date(2021, Calendar.AUGUST, 13);
        Date date02 = new Date(2020, Calendar.MARCH, 1);
        assertEquals("1 year 5 months 12 days ", java7.between(date01, date02));
    }

    @Test
    public void daysInMonth() {
        int[] array = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        assertEquals(Arrays.toString(array), Arrays.toString(java7.daysInMonth(2021)));
    }

    @Test
    public void mondays() {
        Date[] mondays = {new GregorianCalendar(2021, Calendar.MARCH, 1).getTime(),
                new GregorianCalendar(2021, Calendar.MARCH, 8).getTime(),
                new GregorianCalendar(2021, Calendar.MARCH, 15).getTime(),
                new GregorianCalendar(2021, Calendar.MARCH, 22).getTime(),
                new GregorianCalendar(2021, Calendar.MARCH, 29).getTime()};
        assertEquals(Arrays.toString(mondays), Arrays.toString(java7.mondays(2, 2021)));
    }

    @Test
    public void isFridays13() {
        assertEquals(true, java7.isFridays13(new GregorianCalendar(2021,
                Calendar.AUGUST, 13).getTime()));
    }

    @Test
    public void formatFull() {
        assertEquals("2021-08-10", java7.formatFull(new GregorianCalendar(2021,
                Calendar.AUGUST, 10).getTime(), "US"));
    }
}