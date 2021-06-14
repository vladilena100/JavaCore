package com.solution.vasilieva;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReflectionTest {

    private Reflection reflection;

    @Before
    public void initTest() {
        reflection = new Reflection();
    }

    @After
    public void afterTest() {
        reflection = null;
    }

    @Test
    public void testToString() {

        String expected = "{color={colorCar=Black}, good=true}";
        Object obj = new Car(11, true, "Black");
        assertEquals(expected, reflection.toString(obj));
    }

    @Test
    public void isTheSame() {

        Car car1 = new Car(234, true, "Black");
        Car car2 = new Car(185, true, "Black");
        assertEquals(true, reflection.isTheSame(car1, car2));
    }
}