package com.solution.vasilieva;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainExceptions {

    private static final Logger LOG = LogManager.getLogger(MainExceptions.class);

    public static void main(String[] args) {
        String pathToFile = "D:\\study\\NIX\\test.log";
        String message = "MESSAGE: blablabla";
        try {
            MessageLogger messageLogger = new MessageLogger(pathToFile, new Saver());
            messageLogger.log(message);
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }

    }
}
