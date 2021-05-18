package model;

import com.car.Car;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RollsRoysTest {

    @Test
    public void neobkhodimoButilok() {
        RollsRoys rollsRoys1 = new RollsRoys(300, 3.5f,
                5, 50,
                "Sedan", true);
        int people = 3;
        int kolvoBokalovNaOdnogo = 3;
        String result = "шампанского хватит";
        assertEquals(result, rollsRoys1.neobkhodimoButilok(people, kolvoBokalovNaOdnogo));
    }
}