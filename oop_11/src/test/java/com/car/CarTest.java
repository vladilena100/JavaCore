package com.car;

import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;

public class CarTest {
    private Car car;

    @Before
    public void initTest() {
        car = new Car();
    }

    @After
    public void afterTest() {
        car = null;
    }

    @org.junit.Test
    public void razognat() {
        int plusSpeed = 100;
        int maxSpeed = 200;
        assertEquals(100, car.razognat(plusSpeed, maxSpeed));
    }
}