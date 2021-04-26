package com.solutions.vasilieva;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class Main {

    private static final Logger LOG = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        StringsHomeTasks c = new StringsHomeTasks();
        //LOG.info("Task1: " + Arrays.toString(c.ip2Bytes("127.0.237.255")));
        //LOG.info("Task2: " + c.alphabet());
        //LOG.info("Task2: " + c.toCamelCase("HeLlO jAvA, WoRlD"));
        LOG.info("task3 " + Arrays.toString(c.uri2Array("ftp://1.2.3.4:25/pass0/pass1/pass2?a=1&amp;b=2#anchor")));
    }
}
