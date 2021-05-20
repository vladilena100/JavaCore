package com.solution.shapes;

public class Circle extends Shapes{

    Koordinats koordinataA;
    Koordinats koordinataB;

    public Circle(Koordinats koordinataA, Koordinats koordinataB) {
        this.koordinataA = koordinataA;
        this.koordinataB = koordinataB;
    }

    /**
     * The method calculates the area of a Circle
     * @param n side change factor
     * @return
     */

    @Override
    public double ploshad(double n) {

        final double chisloPi = 3.1415926536;

        double[] otrezok = uvelichitStorony(n);

        double radius = otrezok[0];

        return chisloPi * Math.pow(radius, 2);
    }

    /**
     * The method changes the radius of the circle
     * @param koeffitsient side change factor
     * @return
     */

    @Override
    public double[] uvelichitStorony(double koeffitsient) {

        double[] otrezok = new double[1];

        otrezok[0] = dlinaOtrezka(koordinataA, koordinataB);

        if (koeffitsient < 1) {
            otrezok[0] *= koeffitsient;
        }

        if (koeffitsient > 1) {
            otrezok[0] *= koeffitsient;
        }

        return otrezok;
    }
}
