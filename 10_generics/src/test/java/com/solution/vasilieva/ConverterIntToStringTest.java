package com.solution.vasilieva;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConverterIntToStringTest {

    private ConverterIntToString converterIntToString;

    @Before
    public void initTest() {
         converterIntToString = new ConverterIntToString();
    }

    @After
    public void afterTest() {
        converterIntToString = null;
    }

    @Test
    public void get() {

        Integer[] value = {3, 5, 8};
        String result = "3 5 8 ";
        assertEquals(result, converterIntToString.get(value));
    }
}