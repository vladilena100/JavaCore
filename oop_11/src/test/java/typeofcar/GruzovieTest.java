package typeofcar;

import com.car.Car;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GruzovieTest {

    private Gruzovie gruzovie;

    @Before
    public void initTest() {
        gruzovie = new Gruzovie();
    }

    @After
    public void afterTest() {
        gruzovie = null;
    }

    @Test
    public void ostalosMesta() {
        int maxVesGruza = 6000;
        int dobavitVes = 4000;
        assertEquals(2000, gruzovie.ostalosMesta(maxVesGruza, dobavitVes));
    }
}