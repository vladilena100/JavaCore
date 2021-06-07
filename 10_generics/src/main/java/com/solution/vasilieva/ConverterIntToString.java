package com.solution.vasilieva;

import com.nixsolutions.ppp.generics.Converter;

/**
 * Класс реализует конкатенация всех целых чисел массива в строку через пробел
 *
 * @author Vladilena Vasilieva
 * @since 29.05.21
 */

public class ConverterIntToString implements Converter<String, Integer[]> {

    /**
     * конкатенация всех целых чисел массива в строку через пробел
     * @param value
     * @return
     */
    @Override
    public String get(Integer[] value) {

        String result = "";
        for (int i : value) {
            result += String.valueOf(i);
            result += " ";
        }
        return result;
    }
}
