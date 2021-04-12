package com.solution.vasilieva;

import com.nixsolutions.ppp.basetypes.BaseTypesUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Locale;

public class BaseTypes implements BaseTypesUtil {

    /**
     * Метод toggleScientificNotation перевод числа из экспоненциальной
     * формы в нормальную и наоборот.Обычную форму числа в экспоненциальную.
     * @param str входящее число
     * @return возврат отформатированного числа
     */

    @java.lang.Override
    public java.lang.String toggleScientificNotation(java.lang.String str) {
        BigDecimal bigDecimal = new BigDecimal(str).setScale(16, RoundingMode.HALF_UP);
        if (str.contains("e") || str.contains("E")) {
            return bigDecimal.toPlainString();
        } else {
            DecimalFormat decimalFormat = new DecimalFormat("#0.################E0");
            return decimalFormat.format(bigDecimal);
        }
    }

    /**
     * метод sort реализует сортировку массива пузырьком.
     * Чтобы не изменять исходный массив использовался метод clone.
     * @param array исходный массив
     * @return возвращение отсортированного массива
     */

    @java.lang.Override
    public int[] sort(int[] array) {
        boolean isSorted = false;
        int buf;
        int[] arraySort = array.clone();
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < arraySort.length - 1; i++) {
                if (arraySort[i] > arraySort[i + 1]) {
                    isSorted = false;
                    buf = arraySort[i];
                    arraySort[i] = arraySort[i + 1];
                    arraySort[i + 1] = buf;
                }
            }
        }
        return arraySort;
    }

    /**
     * Метод arithmeticMean вычисляет среднее арифметическое всех элементов массива.
     * @param array переданный массив
     * @return возвращение результата
     */

    @java.lang.Override
    public float arithmeticMean(int[] array) {
        float sum = 0;
        int count = 0;
        float average;

        for (int i = 0; i < array.length; i++) {
            count++;
            sum += array[i];
        }
        average = sum / count;

        return average;
    }

    /**
     *  Метод format реализует форматирование числа с плавающей точкой
     *  в соответствии с локалью для указанного языка
     * @param n число для форматирования
     * @param language локаль
     * @return возвращение результата с форматированием
     */

    @java.lang.Override
    public java.lang.String format(double n, java.lang.String language) {
        Locale locale = new Locale(language);
        return String.format(locale, "%f", n);
    }

    /**
     * Метод plus реализует сложение двух чисел произвольных типов и
     * произвольной размерности путем приведения чисел к BigDecimal.
     * Благодаря методу add происходит сложение.
     * @param value1 параметр для сложения №1
     * @param value2 параметр для сложения №2
     * @return возвращение результата
     */

    @java.lang.Override
    public java.lang.String plus(java.lang.String value1, java.lang.String value2) {
        BigDecimal bd1 = new BigDecimal(value1);
        BigDecimal bd2 = new BigDecimal(value2);
        bd1 = bd1.add(bd2);
        return bd1.toString();
    }

    /**
     * Метод minus реализует вычитание двух чисел произвольных типов и
     * произвольной размерности путем приведения чисел к BigDecimal.
     * Благодаря методу subtract происходит вычитание.
     * @param value1 параметр для вычитания №1
     * @param value2 параметр для вычитания №2
     * @return возвращение результата
     */

    @java.lang.Override
    public java.lang.String minus(java.lang.String value1, java.lang.String value2) {
        BigDecimal bd1 = new BigDecimal(value1);
        BigDecimal bd2 = new BigDecimal(value2);
        bd1 = bd1.subtract(bd2);
        return bd1.toString();
    }

    /**
     * Метод mul реализует умножение двух чисел произвольных типов и
     * произвольной размерности путем приведения чисел к BigDecimal.
     * Благодаря методу multiply происходит умножение.
     * @param value1 параметр для умножения №1
     * @param value2 параметр для умножения №2
     * @return возвращение результата
     */

    @java.lang.Override
    public java.lang.String mul(java.lang.String value1, java.lang.String value2) {
        BigDecimal bd1 = new BigDecimal(value1);
        BigDecimal bd2 = new BigDecimal(value2);
        bd1 = bd1.multiply(bd2);
        return bd1.toString();
    }

    /**
     * Метод div реализует деление двух чисел произвольных типов и
     * произвольной размерности путем приведения чисел к BigDecimal.
     * Благодаря методу divide происходит деление и округление.
     * @param value1 параметр для деления №1
     * @param value2 параметр для деления №2
     * @return возвращение результата
     */

    @java.lang.Override
    public java.lang.String div(java.lang.String value1, java.lang.String value2) {
        BigDecimal bd1 = new BigDecimal(value1);
        BigDecimal bd2 = new BigDecimal(value2);
        bd1 = bd1.divide(bd2, RoundingMode.HALF_UP);
        return bd1.toString();
    }
}
