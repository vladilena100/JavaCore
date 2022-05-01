package com.solution.vasilieva;

import com.nixsolutions.ppp.generics.Summator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс суммирует данные List<Integer> для каждого ключа в data
 *
 * @author Vladileva Vasilieva
 * @since 29.05.21
 */

public class SummatorInteger implements Summator<Integer> {

    /**
     * Метод суммирует данные List<Integer> для каждого ключа в data
     * @param data
     * @return
     */
    @Override
    public Map<String, Double> sum(Map<String, List<Integer>> data) {

        HashMap<String, Double> result = new HashMap<>();
        for (Map.Entry<String, List<Integer>> entry : data.entrySet()) {

            List<Integer> value = entry.getValue();
            double sum = 0;
            for (Integer integer : value) {
                sum += integer;
            }
            result.put(entry.getKey(), sum);
        }
        return result;
    }
}
