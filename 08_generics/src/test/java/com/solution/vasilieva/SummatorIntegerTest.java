package com.solution.vasilieva;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class SummatorIntegerTest {

    private SummatorInteger summatorInteger;

    @Before
    public void initTest() {
        summatorInteger = new SummatorInteger();
    }

    @After
    public void afterTest() {
        summatorInteger = null;
    }

    @Test
    public void sum() {

        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);

        List<Integer> list2 = new ArrayList<Integer>();
        list2.add(4);
        list2.add(5);
        list2.add(6);

        Map<String, List<Integer>> map = new HashMap<>();
        map.put("key 1", list);
        map.put("key 2", list2);

        Map<String, Double> mapResult = new HashMap<>();
        mapResult.put("key 1", 6.0);
        mapResult.put("key 2", 15.0);

        assertEquals(mapResult, summatorInteger.sum(map));
    }
}