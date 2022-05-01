package com.solution.vasilieva;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaiGenerics {
    public static void main(String[] args) {

        SummatorBigDecimal summatorBigDecimal = new SummatorBigDecimal();

        List<BigDecimal> list = new ArrayList<BigDecimal>();
        BigDecimal totalQuantity1 = new BigDecimal(1);
        BigDecimal totalQuantity2 = new BigDecimal(2);
        BigDecimal totalQuantity3 = new BigDecimal(3);
        list.add(totalQuantity1);
        list.add(totalQuantity2);
        list.add(totalQuantity3);

        List<BigDecimal> list2 = new ArrayList<BigDecimal>();
        BigDecimal a = new BigDecimal(4);
        BigDecimal b = new BigDecimal(5);
        BigDecimal c = new BigDecimal(6);
        list2.add(a);
        list2.add(b);
        list2.add(c);

        Map<String, List<BigDecimal>> map = new HashMap<>();
        map.put("key 1", list);
        map.put("key 2", list2);

        System.out.println(summatorBigDecimal.sum(map));
    }
}
