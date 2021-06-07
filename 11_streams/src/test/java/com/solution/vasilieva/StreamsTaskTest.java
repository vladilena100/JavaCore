package com.solution.vasilieva;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * класс тестирует методы класса StreamsTask
 *
 * @author Vasilieva Vladilena
 * @since 06.06.21
 */

public class StreamsTaskTest {

    StreamsTask streamsTask;

    @Before
    public void initTest() {
         streamsTask = new StreamsTask();
    }

    @After
    public void afterTest() {
        streamsTask = null;
    }

    @Test
    public void findDistinctToUpperCase() {

        List<String> result = new ArrayList<String>();

        result.add("FOO");
        result.add("BAR");
        result.add("BAZ");

        String[] input = {"foo", "bar", "baz", "foo"};

        assertEquals(result, streamsTask.findDistinctToUpperCase(input));
    }

    @Test
    public void countNegativeIntegers() {

        List<Integer> input = new ArrayList<Integer>();

        input.add(1);
        input.add(-1);
        input.add(13);
        input.add(-3);
        input.add(0);
        input.add(45);
        input.add(-36);

        assertEquals(3, streamsTask.countNegativeIntegers(input));
    }

    @Test
    public void countWordsInList() {

        List<String> input = new ArrayList<String>();

        input.add("FOO");
        input.add("BAR");
        input.add("BAZ");
        input.add("FOS");
        input.add("BAG");
        input.add("BAZ");

        assertEquals(2, streamsTask.countWordsInList(input, "BAZ"));
    }

    @Test
    public void toDistinctList() {

        List<String[]> input = new ArrayList<String[]>();

        input.add(new String[] { "foo", "bar", "baz" });
        input.add(new String[] { "foo", "bar", "fuz" });

        List<String> result = new ArrayList<>();

        result.add("foo");
        result.add("bar");
        result.add("baz");
        result.add("fuz");

        assertEquals(result, streamsTask.toDistinctList(input));
    }

    @Test
    public void isAllStringsLongerThen() {

        List<String> input = new ArrayList<>();

        input.add("foozsc");
        input.add("barg");
        input.add("bazssdc");
        input.add("fuzja");

        assertEquals(true, streamsTask.isAllStringsLongerThen(input, 3));
    }

    @Test
    public void getMaxFiveNumbers() {

        int[] input = {1, 2, 3, 4, 9, 8, 7, 6, 5};

        List<Integer> result = new ArrayList<>();

        result.add(5);
        result.add(6);
        result.add(7);
        result.add(8);
        result.add(9);

        assertEquals(result, streamsTask.getMaxFiveNumbers(input));
    }

    @Test
    public void getStringOfNumbers() {

        int[] input = {1, 53, 63, 23};
        String result = "1,53,63,23";

        assertEquals(result, streamsTask.getStringOfNumbers(input));
    }

    @Test
    public void getFirstCharactersAsString() {

        List<String> input = new ArrayList<>();

        input.add("serial");
        input.add("number");
        input.add("of");
        input.add("turbo");

        String result = "snot";

        assertEquals(result, streamsTask.getFirstCharactersAsString(input));
    }

    @Test
    public void groupByLength() {

        List<String> input = new ArrayList<>();

        input.add("Irene");
        input.add("Wendy");
        input.add("Seulgi");
        input.add("Joy");
        input.add("Yeri");
        input.add("Red");
        input.add("Velvet");

        Map<Integer, List<String>> result = new HashMap<>();

        List<String> list1 = new ArrayList<>();

        list1.add("Joy");
        list1.add("Red");

        List<String> list2 = new ArrayList<>();

        list2.add("Yeri");

        List<String> list3 = new ArrayList<>();

        list3.add("Irene");
        list3.add("Wendy");

        List<String> list4 = new ArrayList<>();

        list4.add("Seulgi");
        list4.add("Velvet");

        result.put(3, list1);
        result.put(4, list2);
        result.put(5, list3);
        result.put(6, list4);

        assertEquals(result, streamsTask.groupByLength(input));

    }
}