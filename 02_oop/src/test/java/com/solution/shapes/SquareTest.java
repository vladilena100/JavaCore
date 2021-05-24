package com.solution.shapes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class SquareTest {

    private Square square;

    @Before
    public void initTest() {

        Koordinats koordinatsA = new Koordinats(32, 15);
        Koordinats koordinatsC = new Koordinats(78, 42);

        square = new Square(koordinatsA, koordinatsC);
    }

    @After
    public void afterTest() {

        square = null;
    }

    @Test
    public void ploshad() {

        double koeffitsient = 0.98;
        assertEquals(1192.8168, 1192.8168, square.ploshad(koeffitsient));
    }

    @Test
    public void uvelichitStorony() {

        double koeffitsient = 0.98;
        double[] array = {26.46, 45.08};
        assertEquals(Arrays.toString(array), Arrays.toString(square.uvelichitStorony(koeffitsient)));
    }
}