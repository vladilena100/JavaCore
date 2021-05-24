package com.solution.shapes;

public class Triangle extends Shapes{

    Koordinats koordinataA;
    Koordinats koordinataB;
    Koordinats koordinataC;

    public Triangle(Koordinats a, Koordinats b, Koordinats c) {
        this.koordinataA = a;
        this.koordinataB = b;
        this.koordinataC = c;
    }

    /**
     * The method calculates the area of a triangle
     * @param n side change factor
     * @return
     */

    @Override
    public double ploshad(double n) {

        double[] otrezki = uvelichitStorony(n);

        double poluPerimetr = (otrezki[0] + otrezki[1] + otrezki[2]) / 2;

        return Math.sqrt(poluPerimetr * (poluPerimetr - otrezki[0])
                * (poluPerimetr - otrezki[1])
                * (poluPerimetr - otrezki[2]));
    }

    /**
     * The method changes the sides of the triangle
     * @param koeffitsient side change factor
     * @return
     */

    @Override
    public double[] uvelichitStorony(double koeffitsient) {

        double[] otrezki = new double[3];

        otrezki[0] = dlinaOtrezka(koordinataA, koordinataB);

        otrezki[1] = dlinaOtrezka(koordinataA, koordinataC);

        otrezki[2] = dlinaOtrezka(koordinataC, koordinataB);

        if (koeffitsient < 1) {
            otrezki[0] *= koeffitsient;
            otrezki[1] *= koeffitsient;
            otrezki[2] *= koeffitsient;
        }

        if (koeffitsient > 1) {
            otrezki[0] *= koeffitsient;
            otrezki[1] *= koeffitsient;
            otrezki[2] *= koeffitsient;
        }

        return otrezki;
    }


}
