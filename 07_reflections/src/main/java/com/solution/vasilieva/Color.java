package com.solution.vasilieva;

import com.nixsolutions.ppp.reflection.Info;

/**
 * Класс для создания составного типа для проверки реализации метода toString
 *
 * @author Vasilieva Vladilena
 * @since 13.06.21
 */

public class Color {

    public Color(String colorCar) {
        this.colorCar = colorCar;
    }

    @Info
    String colorCar;
}
