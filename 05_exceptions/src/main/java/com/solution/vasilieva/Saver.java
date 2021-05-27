package com.solution.vasilieva;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Класс позволяет записать сообщение в файл
 *
 * @author Vladilena Vasilieva
 * @since 23.05.21
 */

public class Saver implements com.nixsolutions.ppp.exceptions.Saver {

    private static final Logger LOG = LogManager.getLogger(Saver.class);

    /**
     * the method writes a string to a file.
     * if the file does not exist creates the file.
     *
     * @param s text
     * @param s1 path to file
     */

    @Override
    public void save(String s, String s1) {
        File file = new File(s1);

        if (file.exists()) {
            try {
                throw new IOException();
            } catch (IOException e) {
                LOG.error(e);
            }
        }

        try (FileWriter writer = new FileWriter(file);){
            writer.write(s);
        } catch (IOException e) {
            LOG.error(e);
        }
    }
}
