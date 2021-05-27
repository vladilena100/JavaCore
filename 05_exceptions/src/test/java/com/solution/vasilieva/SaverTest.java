package com.solution.vasilieva;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.Assert.*;

public class SaverTest {

    private Saver saver;

    @Before
    public void initTest() {
        saver = new Saver();
    }

    @After
    public void afterTest() {
        saver = null;
    }

    @Test
    public void save() {
        String arr1 = "";
        String pathToFile = "D:\\study\\NIX\\test.log";
        File fileName = new File(pathToFile);
        try {
            Scanner sc = new Scanner(fileName);
            while (sc.hasNextLine()) {
                arr1 = sc.nextLine();
            }
            assertNotNull(arr1);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}