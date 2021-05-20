package com.solution.pishushie;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UstroistvaTest {

    private Ruchka ustroistva;

    @Before
    public void initTest() {
        ustroistva = new Ruchka();
    }

    @After
    public void afterTest() {
        ustroistva = null;
    }

    @Test
    public void deletLastLetter() {
        String strok = "abcd";
        StringBuilder str = new StringBuilder(strok);
        String strokaResult = "abc";
        StringBuilder result = new StringBuilder(strokaResult);

        assertEquals(result.toString(), ustroistva.deletLastLetter(str).toString());
    }
}