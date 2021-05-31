package com.solution.vasilieva;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOG = LogManager.getLogger(DefaultCollection.class);

    public static void main(String[] args) {

        DefaultCollection<Integer> defaultCollectionClass = new DefaultCollection<Integer>();
        defaultCollectionClass.add(1);
        defaultCollectionClass.add(2);
        defaultCollectionClass.add(3);
        defaultCollectionClass.clear();
        //LOG.info(Arrays.toString(defaultCollectionClass.toArray()));
        LOG.info(defaultCollectionClass.contains(null));
    }
}
