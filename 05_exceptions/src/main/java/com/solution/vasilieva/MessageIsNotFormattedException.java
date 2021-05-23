package com.solution.vasilieva;

import java.io.IOException;

/**
 * My exception
 */

public class MessageIsNotFormattedException extends IOException {

    public MessageIsNotFormattedException(String message){

        super(message);
    }
}
