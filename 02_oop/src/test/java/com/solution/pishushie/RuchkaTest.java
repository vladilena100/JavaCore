package com.solution.pishushie;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RuchkaTest {

    private Ruchka ruchka;

    @Before
    public void initTest() {
        ruchka = new Ruchka();
    }

    @After
    public void afterTest() {
        ruchka = null;
    }

    @Test
    public void ostatokVeshestva() {
        String stroka = "andjuhbftlknfhrsbhlyghlhytrdnhkl";
        StringBuilder str = new StringBuilder(stroka);
        assertEquals(51.7, 51.7, ruchka.ostatokVeshestva(str));
    }
}