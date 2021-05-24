package com.solution.pishushie;

import java.util.Arrays;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainUstroistva {

    private static final Logger LOG = LogManager.getLogger(MainUstroistva.class);

    public static void main(String[] args) {

        StringBuilder str = new StringBuilder();

        /**
         * filling the array with devices
         */

        Ustroistva[] ustroistva = new Ustroistva[10];
        Ustroistva obj = null;
        Random randomizer = new Random();
        for (int i = 0; i < 10; i++) {
            int randomNumber = randomizer.nextInt(3);
            switch (randomNumber) {
                case 0:
                    obj = new Flomaster();
                    break;
                case 1:
                    obj = new Karandash();
                    break;
                case 2:
                    obj = new Ruchka();
                    break;
            }
            ustroistva[i] = obj;
        }

        StringBuilder simv = new StringBuilder();
        Random rnd = new Random();
        char nextChar;
        double[] ostatok = new double[10];


        /**
         * populating the StringBuilder with each device
         */

        for (int i = 0; i < ustroistva.length; i++) {
            for (int j = 0; j < 10; j++) {
                int kolvoSimv = (int)(3 + Math.random() * 6);
                for (int k = 0; k < kolvoSimv; k++) {
                    nextChar = (char) (rnd.nextInt(94) + 32);
                    simv.append(nextChar);
                }
                str.append(ustroistva[i].deletLastLetter(simv));
            }
            ostatok[i] = ustroistva[i].ostatokVeshestva(str);
        }

        /**
         * sorting by the amount of writing substance
         */

        for (int i = ostatok.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (ostatok[j] > ostatok[j + 1]) {
                    double tmp = ostatok[j];
                    ostatok[j] = ostatok[j + 1];
                    ostatok[j + 1] = tmp;
                }
            }
        }
        LOG.info("остатки пишущего вещества" + Arrays.toString(ostatok));
    }

}
