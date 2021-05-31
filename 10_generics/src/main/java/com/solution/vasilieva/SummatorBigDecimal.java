package com.solution.vasilieva;

import com.nixsolutions.ppp.generics.Summator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс суммирует данные List<BigDecimal> для каждого ключа в data
 *
 * @author Vladileva Vasilieva
 * @since 29.05.21
 */

public class SummatorBigDecimal implements Summator<BigDecimal> {

    /**
     * Метод суммирует данные List<BigDecimal> для каждого ключа в data
     * @param data
     * @return
     */

    @Override
    public Map<String, Double> sum(Map<String, List<BigDecimal>> data) {

        HashMap<String, Double> result = new HashMap<>();
        for (Map.Entry<String, List<BigDecimal>> entry : data.entrySet()) {

            List<BigDecimal> value = entry.getValue();
            double sum = 0;
            for (BigDecimal bigDecimal : value) {
                sum += bigDecimal.doubleValue();
            }
            result.put(entry.getKey(), sum);
        }
        return result;
    }
}
