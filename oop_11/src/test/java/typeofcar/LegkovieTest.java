package typeofcar;

import com.car.Car;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LegkovieTest {

    private Legkovie legkovie;

    @Before
    public void initTest() {
        legkovie = new Legkovie();
    }

    @After
    public void afterTest() {
        legkovie = null;
    }

    @Test
    public void ostatokMesta() {
        int obemBagazhnika = 60;
        int predmet = 30;
        assertEquals(30, legkovie.ostatokMesta(obemBagazhnika, predmet));
    }
}