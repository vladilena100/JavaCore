package com.solution.vasilieva;

import com.nixsolutions.ppp.exceptions.OneMessageFileLogger;

import java.io.IOException;

/**
 * Класс позволяет записать и проверить на соответствие
 * записанное сообщение
 *
 * @author Vladilena Vasilieva
 * @since 23.05.21
 */

public class MessageLogger implements OneMessageFileLogger {

    String path;
    Saver saver;
    String MESSAGE_STARTS_WITH = "MESSAGE: ";

    public MessageLogger() { }

    public MessageLogger(String path, Saver saver) {
        this.path = path;
        this.saver = saver;
    }

    /**
     * method calculates the correctness of the beginning of the line
     * @param s
     * @throws IOException
     */

    @Override
    public void log(String s) throws IOException {
        if (s.startsWith(MESSAGE_STARTS_WITH)) {
            saver.save(s, path);
        } else {
            throw new MessageIsNotFormattedException("Неверное начало строки " + s);
        }
    }
}
