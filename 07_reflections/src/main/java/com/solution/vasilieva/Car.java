package com.solution.vasilieva;

import com.nixsolutions.ppp.reflection.Ignore;
import com.nixsolutions.ppp.reflection.Info;

/**
 * Класс для проверки метода toString класса Reflection
 *
 * @author Vasilieva Vladilena
 * @since 13.06.21
 */

public class Car {

    public Car(int speed, boolean good, String color) {
        this.speed = speed;
        this.good = good;
        this.color = new Color(color);
    }

    @Ignore
    int speed;

    @Info
    boolean good;

    @Info
    Color color;
}
