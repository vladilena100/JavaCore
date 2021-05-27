package com.solution.vasilieva;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DefaultIteratorTest {

    Object[] a = {1, 2, 3};
    DefaultCollection.DefaultIteratorClass iterator = new DefaultCollection.DefaultIteratorClass(a);

    @Test
    public void hasNext() {

        assertEquals(true, iterator.hasNext());

    }

    @Test
    public void next() {

        assertEquals(1, iterator.next());

    }
}
