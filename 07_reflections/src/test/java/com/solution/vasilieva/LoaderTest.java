package com.solution.vasilieva;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoaderTest {

    private Loader loader;

    @Before
    public void initTest() {
        loader = new Loader();
    }

    @After
    public void afterTest() {
        loader = null;
    }

    @Test
    public void findClass() {
    }
}