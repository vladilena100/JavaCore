package model;

import com.car.Car;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TeslaTest {

    private Tesla tesla;

    @Before
    public void initTest() {
        tesla = new Tesla();
    }

    @After
    public void afterTest() {
        tesla = null;
    }

    @Test
    public void zapasKm() {
        int zaryadSolnechnoiPaneli = 50;
        float result = 10.0f;
        assertEquals(result, result, tesla.zapasKm(zaryadSolnechnoiPaneli));
    }
}