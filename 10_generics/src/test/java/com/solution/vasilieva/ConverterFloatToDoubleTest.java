package com.solution.vasilieva;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConverterFloatToDoubleTest {

    private ConverterFloatToDouble converterFloatToDouble;

    @Before
    public void initTest() {
        converterFloatToDouble = new ConverterFloatToDouble();
    }

    @After
    public void afterTest() {
        converterFloatToDouble = null;
    }

    @Test
    public void get() {

        float value = 3.2f;
        double result = 3.2;
        assertEquals(result, converterFloatToDouble.get(value), 0.1);
    }
}