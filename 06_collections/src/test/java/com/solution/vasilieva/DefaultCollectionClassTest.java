package com.solution.vasilieva;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

import static org.junit.Assert.*;

public class DefaultCollectionClassTest {

    private static final Logger LOG = LogManager.getLogger(DefaultCollectionClassTest.class);

    private DefaultCollection defaultCollectionClass;

    @Before
    public void initTest() {
        DefaultCollection defaultCollectionClass = new DefaultCollection();
    }

    @After
    public void afterTest() {
        defaultCollectionClass = null;
    }

    @Test
    public void size() {
        DefaultCollection<Integer> defaultCollectionClass = new DefaultCollection<Integer>();
        defaultCollectionClass.add(1);
        defaultCollectionClass.add(2);
        defaultCollectionClass.add(3);
        assertEquals(3, defaultCollectionClass.size());
    }

    @Test
    public void isEmpty() {
        DefaultCollection<Integer> defaultCollectionClass = new DefaultCollection<Integer>();
        defaultCollectionClass.add(1);
        defaultCollectionClass.add(2);
        defaultCollectionClass.add(3);
        assertEquals(false, defaultCollectionClass.isEmpty());
    }

    @Test
    public void contains() {
        DefaultCollection<Integer> defaultCollectionClass = new DefaultCollection<Integer>();
        defaultCollectionClass.add(1);
        defaultCollectionClass.add(2);
        defaultCollectionClass.add(null);
        defaultCollectionClass.add(3);
        assertEquals(true, defaultCollectionClass.contains(null));
    }

    @Test
    public void iterator() {
    }

    @Test
    public void toArray() {
    }

    @Test
    public void add() {
        DefaultCollection<Integer> defaultCollectionClass = new DefaultCollection<Integer>();
        defaultCollectionClass.add(1);
        defaultCollectionClass.add(2);
        defaultCollectionClass.add(3);
        assertEquals(true, defaultCollectionClass.add(4));
    }

    @Test
    public void remove() {
        DefaultCollection<Integer> defaultCollectionClass = new DefaultCollection<Integer>();
        defaultCollectionClass.add(1);
        defaultCollectionClass.add(2);
        defaultCollectionClass.add(3);
        assertEquals(true, defaultCollectionClass.remove(2));
    }

    @Test
    public void addAll() {
        DefaultCollection<Integer> defaultCollectionClass = new DefaultCollection<Integer>();
        defaultCollectionClass.add(1);
        defaultCollectionClass.add(2);
        defaultCollectionClass.add(3);
        DefaultCollection<Integer> second = new DefaultCollection<Integer>();
        defaultCollectionClass.add(4);
        defaultCollectionClass.add(5);
        defaultCollectionClass.add(6);
        assertEquals(true, defaultCollectionClass.addAll(second));
    }

    @Test
    public void clear() {
        DefaultCollection<Integer> defaultCollectionClass = new DefaultCollection<Integer>();
        defaultCollectionClass.add(1);
        defaultCollectionClass.add(2);
        defaultCollectionClass.add(3);

        boolean result = false;
        defaultCollectionClass.clear();
        LOG.info(Arrays.toString(defaultCollectionClass.toArray()));
        assertEquals(true, defaultCollectionClass.contains(null));
    }

    @Test
    public void retainAll() {
        DefaultCollection<Integer> defaultCollectionClass = new DefaultCollection<Integer>();
        defaultCollectionClass.add(1);
        defaultCollectionClass.add(2);
        defaultCollectionClass.add(3);
        DefaultCollection<Integer> second = new DefaultCollection<Integer>();
        defaultCollectionClass.add(1);
        defaultCollectionClass.add(6);
        defaultCollectionClass.add(3);
        assertEquals(true, defaultCollectionClass.retainAll(second));
    }

    @Test
    public void removeAll() {
        DefaultCollection<Integer> defaultCollectionClass = new DefaultCollection<Integer>();
        defaultCollectionClass.add(1);
        defaultCollectionClass.add(2);
        defaultCollectionClass.add(3);
        DefaultCollection<Integer> second = new DefaultCollection<Integer>();
        defaultCollectionClass.add(1);
        defaultCollectionClass.add(6);
        defaultCollectionClass.add(3);
        assertEquals(true, defaultCollectionClass.retainAll(second));
    }

    @Test
    public void containsAll() {
        DefaultCollection<Integer> defaultCollectionClass = new DefaultCollection<Integer>();
        defaultCollectionClass.add(1);
        defaultCollectionClass.add(2);
        defaultCollectionClass.add(3);
        DefaultCollection<Integer> second = new DefaultCollection<Integer>();
        defaultCollectionClass.add(1);
        defaultCollectionClass.add(2);
        defaultCollectionClass.add(3);
        assertEquals(true, defaultCollectionClass.containsAll(second));
    }

    @Test
    public void testToArray() {
    }
}