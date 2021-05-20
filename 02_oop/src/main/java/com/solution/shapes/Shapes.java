package com.solution.shapes;

public abstract class Shapes {

    /**
     * the method calculates the length of the segment by coordinates
     * @param koordinataA
     * @param koordinataB
     * @return
     */

    public double dlinaOtrezka (Koordinats koordinataA, Koordinats koordinataB) {

        return Math.sqrt(Math.pow((koordinataB.getX() - koordinataA.getX()) , 2)
                + Math.pow((koordinataB.getY() - koordinataA.getY()) , 2));
    }

    /**
     * The method calculates the area of a Shapes
     * @param n side change factor
     * @return
     */

    public abstract double ploshad(double n);

    /**
     * The method changes the sides of the Shapes
     * @param koeffitsient side change factor
     * @return
     */

    public abstract double[] uvelichitStorony(double koeffitsient);

}
