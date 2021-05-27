package com.solution.vasilieva;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.Assert.*;

public class MessageLoggerTest {

    private MessageLogger messageLogger;

    @Before
    public void initTest() {
        MessageLogger messageLogger = new MessageLogger();
    }

    @After
    public void afterTest() {
        messageLogger = null;
    }

    @Test
    public void log() {
        String arr1 = "";
        String pathToFile = "D:\\study\\NIX\\test.log";
        String result = "MESSAGE: blablabla";
        File fileName = new File(pathToFile);
        try {
            Scanner sc = new Scanner(fileName);
            while (sc.hasNextLine()) {
                arr1 = sc.nextLine();
            }
            assertEquals(arr1, result);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}