package com.solution.vasilieva;

import org.junit.After;
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
        int[] values = new int[5];
    }

    @Test
    public void isEmpty() {
        int[] values = new int[5];
    }

    @Test
    public void contains() {
    }

    @Test
    public void iterator() {
    }

    @Test
    public void toArray() {
    }

    @Test
    public void add() {
    }

    @Test
    public void remove() {
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