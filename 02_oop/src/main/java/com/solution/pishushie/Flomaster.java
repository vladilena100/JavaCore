package com.solution.pishushie;

public class Flomaster extends Ustroistva {

    private final int raskhodDo20Simvolov = 1;
    private final double raskhodOt21Do40Simvolov = 1.09;
    private final double raskhodOt40Simvolov = 1.21;

    /**
     * method calculates the remainder of the substance
     * @param str input StringBuilder
     * @return
     */

    @Override
    public double ostatokVeshestva(StringBuilder str) {

        double result = getProtsentVeshestva();

        for (int i = 1; i <= str.length() + 10; i++) {

            if(i <= 20){
                result = getProtsentVeshestva() - raskhodDo20Simvolov;
            }

            if((i >= 21) && (i <= 40)) {
                result -= raskhodOt21Do40Simvolov;
            }

            if(i > 40) {
                result -= raskhodOt40Simvolov;
            }

        }
        return result;
    }
}
