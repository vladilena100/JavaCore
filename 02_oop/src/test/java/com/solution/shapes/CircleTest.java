package com.solution.shapes;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class CircleTest {

    private Circle circle;

    @Before
    public void initTest() {

        Koordinats koordinatsA = new Koordinats(32, 15);
        Koordinats koordinatsB = new Koordinats(78, 42);

        circle = new Circle(koordinatsA, koordinatsB);
    }

    @After
    public void afterTest() {

        circle = null;
    }

    @Test
    public void ploshad() {
        double koeffitsient = 0.98;
        assertEquals(8583.892987, 8583.892987, circle.ploshad(koeffitsient));
    }

    @Test
    public void uvelichitStorony() {

        double koeffitsient = 0.98;
        double[] array = {52.27177058413078};
        assertEquals(Arrays.toString(array), Arrays.toString(circle.uvelichitStorony(koeffitsient)));

    }
}