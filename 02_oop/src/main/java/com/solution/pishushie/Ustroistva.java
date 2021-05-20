package com.solution.pishushie;

public abstract class Ustroistva {

    private final int protsentVeshestva = 100;

    public int getProtsentVeshestva() {
        return protsentVeshestva;
    }

    /**
     * deleting the last letter in a line
     * @param str
     * @return
     */

    public StringBuilder deletLastLetter(StringBuilder str) {
        int lastLetter = str.length() - 1;
        str.deleteCharAt(lastLetter);
        return str;
    }

    public abstract double ostatokVeshestva(StringBuilder str);
}
