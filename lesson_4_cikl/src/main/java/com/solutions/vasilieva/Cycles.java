package com.solutions.vasilieva;

import java.lang.reflect.Array;
import java.util.*;

public class Cycles {

    /**
     * Метод позволяет найти сумму четных цифр, входящих в состав числа
     *
     * @param x входящее число
     * @return
     */

    public static int sum(int x) {
        String s = Integer.toString(x);
        int[] arr = new int[s.length()];
        for (int i = s.length() - 1; i >= 0; i--) {
            arr[i] = x % 10;
            x /= 10;
        }
        int summa = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 0) {
                summa += arr[i];
            }
        }
        return summa;

    }

    /**
     * Метод позволяет найти и вывести все цифры числа, которые больше 5
     *
     * @param x входящее число
     * @return
     */

    public static ArrayList<Integer> bolshePyati(int x) {
        String s = Integer.toString(x);
        int[] arr = new int[s.length()];
        for (int i = s.length() - 1; i >= 0; i--) {
            arr[i] = x % 10;
            x /= 10;
        }

        ArrayList<Integer> arr1 = new ArrayList<Integer>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 5) {
                arr1.add(arr[i]);
            }
        }
        return arr1;
    }

    /**
     * Метод позволяет из натурального числа удалить заданную цифру
     *
     * @param x входящее число
     * @param a число которое необходимо удалить
     * @return
     */

    public static ArrayList<Integer> delitNumb(int x, int a) {

        String s = Integer.toString(x);
        //int[] arr = new int[s.length()];
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for (int i = s.length() - 1; i >= 0; i--) {
            arr.add(x % 10);
            x /= 10;
        }
        Collections.reverse(arr);
        Iterator<Integer> arrIterator = arr.iterator();
        while (arrIterator.hasNext()) {

            Integer nextInt = arrIterator.next();
            if (nextInt.equals(a)) {
                arrIterator.remove();
            }
        }
        return arr;
    }

    /**
     * Метод позволяет вывести наибольшую цифру числа
     *
     * @param x входящее число
     * @return
     */

    public static int max(int x) {
        String s = Integer.toString(x);
        int[] arr = new int[s.length()];
        for (int i = s.length() - 1; i >= 0; i--) {
            arr[i] = x % 10;
            x /= 10;
        }
        int max = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    /**
     * Метод позволяет найти все простые числа в диапазоне между двух чисел (включительно)
     *
     * @param x параметр от которого находятся числа
     * @param y параметр до которого находятся числа
     * @return
     */

    public static ArrayList<Integer> prostieDiap(int x, int y) {
        ArrayList<Integer> arr1 = new ArrayList<Integer>();
        boolean isprime;
        for (int i = x; i < y; i++) {
            isprime = true;

            for (int j = 2; j <= i / j; j++)
                if ((i % j) == 0) {
                    isprime = false;
                }
            if (isprime) {
                arr1.add(i);
            }
        }
        return arr1;
    }

    /**
     * Метод позволяет найти в числе все цифры, которые соответствуют простым числам
     *
     * @param x входящее число
     * @return
     */

    public static ArrayList<Integer> prostieVChisle(int x) {
        String s = Integer.toString(x);
        int[] arr = new int[s.length()];
        for (int i = s.length() - 1; i >= 0; i--) {
            arr[i] = x % 10;
            x /= 10;
        }

        ArrayList<Integer> arr1 = new ArrayList<Integer>();
        boolean isprime;
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] == 2) || (arr[i] == 3) || (arr[i] == 5) || (arr[i] == 7)) {
                arr1.add(arr[i]);
            }
        }
        return arr1;
    }

    /**
     * Метод позволяет найти наибольшую сумму пары соседних цифр заданного числа
     *
     * @param x входящее число
     * @return
     */
    public static int maxPair(int x) {
        int doubleDigit;
        int maxSum = 0;
        int sum;
        while (x != 0) {
            doubleDigit = x % 100;
            sum = (doubleDigit % 10) + (doubleDigit / 10);
            if (sum > maxSum) {
                maxSum = sum;
            }
            x = x / 10;
        }
        return maxSum;
    }

    /**
     * Метод позволяет найти самую длинную, увеличивающуюся последовательность цифр в
     * числе
     *
     * @param x входящее число
     * @return
     */
    public static String theLongestSequence(int x) {
        String stringNumber = Integer.toString(x);
        int count = 1;
        int max = 1;
        int iStart = 0;
        for (int i = 1; i < stringNumber.length(); i++) {
            if (stringNumber.charAt(i - 1) == stringNumber.charAt(i) - 1) {
                count++;
            } else {
                count = 1;
            }
            if (count > max) {
                max = count;
                iStart = i + 1 - count;
            }
        }
        return stringNumber.substring(iStart, iStart + max);
    }

    /**
     * Метод позволяет найти простые числа(о) в середине диапазона между двух чисед
     * @param start начало диапазона
     * @param end предел диапазона
     * @return
     */
    public static String findPrimeNumbersInTheMidRange(int start, int end) {
        List<Integer> integerList = new ArrayList<>();
        int[] output;
        boolean prime;
        for (int i = start; i < end; i++) {
            prime = true;
            for (int j = 2; j <= i / j; j++) {
                if (i % j == 0) {
                    prime = false;
                    break;
                }
            }
            if (prime) {
                integerList.add(i);
            }
        }
        if (integerList.size() % 2 == 0) {
            output = new int[2];
            output[0] = integerList.get((integerList.size() / 2) - 1);
            output[1] = integerList.get((integerList.size() / 2));
        } else {
            output = new int[1];
            output[0] = integerList.get((integerList.size() / 2));
        }
        return Arrays.toString(output);
    }
}


