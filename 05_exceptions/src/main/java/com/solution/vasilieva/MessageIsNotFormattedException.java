package com.solution.vasilieva;

import java.io.IOException;

/**
 * Исключения для обработки событий когда сообщение не соответствует формату
 *
 * @author Vladilena Vasilieva
 * @since 23.05.21
 */

public class MessageIsNotFormattedException extends IOException {

    public MessageIsNotFormattedException(String message){

        super(message);
    }
}
