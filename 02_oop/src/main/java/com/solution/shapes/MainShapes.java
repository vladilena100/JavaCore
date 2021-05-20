package com.solution.shapes;

import java.util.Arrays;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainShapes {

    private static final Logger LOG = LogManager.getLogger(MainShapes.class);

    private static final int MAX = Integer.MAX_VALUE;

    /**
     * random number generation
     * @param random
     * @return
     */

    private static Koordinats randomKoordinata(Random random) {
        return new Koordinats(Math.random() + MAX,
                Math.random() + MAX);
    }

    public static void main(String[] args) {

        Shapes[] shapes = new Shapes[10];
        Shapes obj = null;
        Random randomizer = new Random();

        /**
         * filling the array with devices
         */

        for (int i = 0; i < 10; i++) {
            int randomNumber = randomizer.nextInt(3);
            switch (randomNumber) {
                case 0:
                    obj = new Triangle(randomKoordinata(randomizer), randomKoordinata(randomizer),
                            randomKoordinata(randomizer));
                    break;
                case 1:
                    obj = new Circle(randomKoordinata(randomizer), randomKoordinata(randomizer));
                    break;
                case 2:
                    obj = new Square(randomKoordinata(randomizer), randomKoordinata(randomizer));
                    break;
            }
            shapes[i] = obj;
        }

        double[] ploshadi = new double[shapes.length];

        /**
         * calculating the area for each device
         */

        for (int i = 0; i < shapes.length; i++) {
            double koeffitsient = Math.random() * 3;
            ploshadi[i] = shapes[i].ploshad(koeffitsient);
        }

        /**
         * Sort Ascending
         */

        for (int i = ploshadi.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (ploshadi[j] > ploshadi[j + 1]) {
                    double tmp = ploshadi[j];
                    ploshadi[j] = ploshadi[j + 1];
                    ploshadi[j + 1] = tmp;
                }
            }
        }
        LOG.info("площади фигур" + Arrays.toString(ploshadi));
    }
}
