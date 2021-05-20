package typeofcar;

import com.car.Car;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpetsTekhnikaTest {

    private SpetsTekhnika spetsTekhnika;

    @Before
    public void initTest() {
        spetsTekhnika = new SpetsTekhnika();
    }

    @After
    public void afterTest() {
        spetsTekhnika = null;
    }

    @Test
    public void nuzhniyTypChassie() {
        String result = "гусенечный";
        assertEquals(result, spetsTekhnika.nuzhniyTypChassie(1));
    }
}