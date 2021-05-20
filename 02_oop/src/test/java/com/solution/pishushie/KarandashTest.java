package com.solution.pishushie;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class KarandashTest {

    private Karandash karandash;

    @Before
    public void initTest() {
        karandash = new Karandash();
    }

    @After
    public void afterTest() {
        karandash = null;
    }

    @Test
    public void ostatokVeshestva() {
        String stroka = "andjuhbftlknfhrsbhlyghlhytrdnhkl";
        StringBuilder str = new StringBuilder(stroka);
        assertEquals(54.1, 54.1, karandash.ostatokVeshestva(str));
    }
}