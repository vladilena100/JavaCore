package com.solution.vasilieva;

/**
 * Класс реализует классический калькулятор на 4 математические операции
 * @author
 * @since 03.06.21
 */
public class Calculator {

    /**
     * метод производит операцию сложения
     * @param x
     * @param y
     * @return
     */

    public int plus(int x, int y) {
        return x + y;
    }

    /**
     * метод производит операцию вычитания
     * @param x
     * @param y
     * @return
     */

    public int minus(int x, int y) {
        return x - y;
    }

    /**
     * метод производит операцию умножения
     * @param x
     * @param y
     * @return
     */

    public int umnozhit(int x, int y) {
        return x * y;
    }

    /**
     * метод производит операцию деления
     * @param x
     * @param y
     * @return
     */

    public double delit(int x, int y) {
        return x / y;
    }

}
