package com.solution.vasilieva;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.junit.Assert.*;

public class DefaultCollectionClassTest {

    private static final Logger LOG = LogManager.getLogger(DefaultCollectionClassTest.class);

    private DefaultCollectionClass defaultCollectionClass;

    @Before
    public void initTest() {
        DefaultCollectionClass defaultCollectionClass = new DefaultCollectionClass();
    }

    @After
    public void afterTest() {
        defaultCollectionClass = null;
    }

    @Test
    public void size() {
        DefaultCollectionClass<Integer> defaultCollectionClass = new DefaultCollectionClass<Integer>();
        defaultCollectionClass.add(1);
        defaultCollectionClass.add(2);
        defaultCollectionClass.add(3);
        assertEquals(3, defaultCollectionClass.size());
    }

    @Test
    public void isEmpty() {
        DefaultCollectionClass<Integer> defaultCollectionClass = new DefaultCollectionClass<Integer>();
        defaultCollectionClass.add(1);
        defaultCollectionClass.add(2);
        defaultCollectionClass.add(3);
        assertEquals(false, defaultCollectionClass.isEmpty());
    }

    @Test
    public void contains() {
        DefaultCollectionClass<Integer> defaultCollectionClass = new DefaultCollectionClass<Integer>();
        defaultCollectionClass.add(1);
        defaultCollectionClass.add(2);
        defaultCollectionClass.add(3);
        assertEquals(false, defaultCollectionClass.contains(2));
    }

    @Test
    public void iterator() {
    }

    @Test
    public void toArray() {
    }

    @Test
    public void add() {
        DefaultCollectionClass<Integer> defaultCollectionClass = new DefaultCollectionClass<Integer>();
        defaultCollectionClass.add(1);
        defaultCollectionClass.add(2);
        defaultCollectionClass.add(3);
        assertEquals(true, defaultCollectionClass.add(4));
    }

    @Test
    public void remove() {
        DefaultCollectionClass<Integer> defaultCollectionClass = new DefaultCollectionClass<Integer>();
        defaultCollectionClass.add(1);
        defaultCollectionClass.add(2);
        defaultCollectionClass.add(3);
        assertEquals(true, defaultCollectionClass.remove(2));
    }

    @Test
    public void addAll() {
    }

    @Test
    public void clear() {
    }

    @Test
    public void retainAll() {
    }

    @Test
    public void removeAll() {
    }

    @Test
    public void containsAll() {
    }

    @Test
    public void testToArray() {
    }
}