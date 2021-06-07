package com.solution.vasilieva;

import com.nixsolutions.ppp.generics.Converter;

/**
 * Класс преобразовывает число Float в Double
 *
 * @author Vladilena Vasilieva
 * @since 29.05.21
 */

public class ConverterFloatToDouble implements Converter<Double, Float> {

    /**
     * Метод преобразовывает число Float в Double
     *
     * @param value
     * @return
     */

    @Override
    public Double get(Float value) {

        return Double.valueOf(value);
    }
}
