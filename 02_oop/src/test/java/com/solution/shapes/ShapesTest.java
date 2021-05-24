package com.solution.shapes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ShapesTest {

    private Circle shapes;

    @Before
    public void initTest() {

        Koordinats koordinatsA = new Koordinats(32, 15);
        Koordinats koordinatsB = new Koordinats(78, 42);

        shapes = new Circle(koordinatsA, koordinatsB);
    }

    @After
    public void afterTest() {

        shapes = null;
    }

    @Test
    public void dlinaOtrezka() {

        assertEquals(53.33854, 53.33854, shapes.dlinaOtrezka(shapes.koordinataA, shapes.koordinataB));
    }
}