package com.solution.pishushie;

public class Karandash extends Ustroistva {

    private final double raskhodKarandasha = 0.95;

    /**
     * method calculates the remainder of the substance
     * @param str input StringBuilder
     * @return
     */

    @Override
    public double ostatokVeshestva(StringBuilder str) {
        double result = getProtsentVeshestva();
        for(int i = 1; i <= str.length() + 10; i++) {
            result -= raskhodKarandasha;

            if(i % 20 == 0) {
                result -= 3;
            }
        }
        return result;
    }
}
