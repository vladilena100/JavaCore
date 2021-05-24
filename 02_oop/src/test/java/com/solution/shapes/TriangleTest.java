package com.solution.shapes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class TriangleTest {

    private Triangle triangle;

    @Before
    public void initTest() {

        Koordinats koordinatsA = new Koordinats(30, 17);
        Koordinats koordinatsB = new Koordinats(6, 23);
        Koordinats koordinatsC = new Koordinats(78, 42);

        triangle = new Triangle(koordinatsA, koordinatsB, koordinatsC);
    }

    @After
    public void afterTest() {

        triangle = null;
    }

    @Test
    public void ploshad() {

        double koeffitsient = 1.3;
        assertEquals(9.9830034343377844998488408629582, 9.9830034343377844998488408629582,
                triangle.ploshad(koeffitsient));
    }

    @Test
    public void uvelichitStorony() {

//        double koeffitsient = 1.3;
//        double[] array = {32.16022387981775, 70.356307464221, 96.80418379388362};
//        assertEquals(Arrays.toString(array), Arrays.toString(triangle.uvelichitStorony(koeffitsient)));
    }
}