package com.solution.pishushie;

public class Ruchka extends Ustroistva {

    private final double raskhodRuchki = 1.15;


    /**
     * method calculates the remainder of the substance
     * @param str input StringBuilder
     * @return
     */

    @Override
    public double ostatokVeshestva(StringBuilder str) {
        double result = getProtsentVeshestva();
        for(int i = 0; i < str.length() + 10; i++) {
            result -= raskhodRuchki;
        }
        return result;
    }
}
