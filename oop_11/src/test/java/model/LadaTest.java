package model;

import com.car.Car;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LadaTest {

    private Lada lada;

    @Before
    public void initTest() {
        lada = new Lada();
    }

    @After
    public void afterTest() {
        lada = null;
    }

    @Test
    public void prohodRyby() {
        int diametrDirkiDlyaZimneyRibalki = 30;
        int diametrRyby = 12;
        String result = "можно достать через дырку для зимней рыбалки";
        assertEquals(result, lada.prohodRyby(diametrDirkiDlyaZimneyRibalki, diametrRyby));
    }
}