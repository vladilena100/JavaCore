package com.solution.shapes;

public class Square extends Shapes {

    Koordinats koordinataA;
    Koordinats koordinataC;

    public Square(Koordinats koordinataA, Koordinats koordinataC) {
        this.koordinataA = koordinataA;
        this.koordinataC = koordinataC;
    }

    /**
     * The method calculates the area of a Square
     * @param n side change factor
     * @return
     */

    @Override
    public double ploshad(double n) {

        double[] otrezki = uvelichitStorony(n);

        return otrezki[0] * otrezki[1];
    }

    /**
     * The method changes the sides of the Square
     * @param koeffitsient side change factor
     * @return
     */

    @Override
    public double[] uvelichitStorony(double koeffitsient) {

        Koordinats koordinataB = new Koordinats(koordinataA.getX(), koordinataC.getY());

        double[] otrezki = new double[2];

        otrezki[0] = dlinaOtrezka(koordinataA, koordinataB);

        otrezki[1] = dlinaOtrezka(koordinataB, koordinataC);

        if (koeffitsient < 1) {
            otrezki[0] *= koeffitsient;
            otrezki[1] *= koeffitsient;
        }

        if (koeffitsient > 1) {
            otrezki[0] *= koeffitsient;
            otrezki[1] *= koeffitsient;
        }

        return otrezki;
    }
}
