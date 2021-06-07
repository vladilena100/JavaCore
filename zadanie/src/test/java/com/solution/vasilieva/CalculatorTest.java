package com.solution.vasilieva;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {

    private Calculator calculator;

    @Before
    public void initTest() {
        calculator = new Calculator();
    }

    @After
    public void afterTest() {
        calculator = null;
    }

    @Test
    public void plus() {
        int x = 20;
        int y = 10;
        int res = 30;
        assertEquals(res, calculator.plus(x, y));
    }

    @Test
    public void minus() {
        int x = 22;
        int y = 10;
        int res = 12;
        assertEquals(res, calculator.minus(x, y));
    }

    @Test
    public void umnozhit() {
        int x = 2;
        int y = 10;
        int res = 20;
        assertEquals(res, calculator.umnozhit(x, y));
    }

    @Test
    public void delit() {
        int x = 50;
        int y = 2;
        int res = 25;
        assertEquals(res, res, calculator.delit(x, y));
    }
}