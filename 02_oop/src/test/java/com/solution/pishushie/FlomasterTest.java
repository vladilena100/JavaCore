package com.solution.pishushie;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FlomasterTest {

    private Flomaster flomaster;

    @Before
    public void initTest() {
        flomaster = new Flomaster();
    }

    @After
    public void afterTest() {
        flomaster = null;
    }

    @Test
    public void ostatokVeshestva() {
        String stroka = "andjuhbftlknfhrsbhlyghlhytrdnhkl";
        StringBuilder str = new StringBuilder(stroka);
        assertEquals(55.78, 55.78,flomaster.ostatokVeshestva(str));
    }
}