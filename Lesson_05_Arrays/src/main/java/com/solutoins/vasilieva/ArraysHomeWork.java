package com.solutoins.vasilieva;

import java.util.*;
import java.util.stream.Collectors;
import java.util.Arrays;

public class ArraysHomeWork {

    /**
     * Возвращение всех элементов которые больше предыдущих
     *
     * @param arr
     * @return
     */
    public static ArrayList<Integer> bolshePred(int arr[]) {
        ArrayList<Integer> arr1 = new ArrayList<Integer>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < arr[i + 1]) {
                arr1.add(arr[i + 1]);
            }
        }
        return arr1;
    }

    /**
     * Метод меняет местами минимальный и максимальный элементы массива
     *
     * @param arr
     * @return
     */

    public static int[] obmenMestami(int arr[]) {
        int max = 0;
        int max_index = 0;
        int min = arr[0];
        int min_index = 0;
        for (int i = 0; i < arr.length; i++) {

            if (arr[i] > max) {
                max = arr[i];
                max_index = i;
            }
            if (arr[i] < min) {
                arr[i] = min;
            }
        }
        int buf = arr[max_index];
        arr[max_index] = arr[min_index];
        arr[min_index] = buf;
        return arr;
    }

    /**
     * Метод находит сумму четных отрицательных чисел
     *
     * @param arr
     * @return
     */
    public static int sum(int arr[]) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] < 0) && (arr[i] % 2 == 0)) {
                sum += arr[i];
            }
        }
        return sum;
    }

    /**
     * Метод позволяет найти самую длинную последовательность и вывести её
     * если их две вывести обе
     *
     * @param arr
     * @return
     */

    public String Posledovatelnosti(int[] arr) {
        StringBuilder a = new StringBuilder();
        List<String> list = new ArrayList<>();
        int count = 1;
        int max = 1;
        int first = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] < arr[i]) {
                count++;
                if (count >= max) {
                    max = count;
                    first = i + 1 - count;
                }
            }
            if ((arr[i - 1] > arr[i]) || (i == arr.length - 1)) {
                a.setLength(0);
                for (int j = first; j < count + first; j++) {
                    a.append(arr[i]);
                }
                list.add(a.toString());
                count = 1;
            }
        }
        a.setLength(0);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).length() == max) {
                a.append(list.get(i)).append(" ");
            }
        }
        return Arrays.toString(arr) + "\n" + a.toString();
    }

    /**
     * Метод позволяет узнать сколько айсбергов одинакового размера находится в массиве
     *
     * @param arr
     * @return
     */

    public static ArrayList<String> icebergs(int arr[][]) {
        int a = 2;
        ArrayList<Integer> arr1 = new ArrayList<Integer>();
        ArrayList<Integer> arr2 = new ArrayList<Integer>();
        ArrayList<String> arr3 = new ArrayList<String>();

        /* присваеваем каждому айсбергу индивидуальное число */

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 1) {
                    arr[i][j] = a;
                    arr1.add(a);
                    a++;
                    if (j != arr[i].length - 1) {
                        if (arr[i][j + 1] == 1) {
                            arr[i][j + 1] = arr[i][j];
                        }
                    }
                    if (i != arr.length - 1) {
                        if (arr[i + 1][j] == 1) {
                            arr[i + 1][j] = arr[i][j];
                        }
                    }
                }
                if ((arr[i][j] > 1) && (j != arr[i].length - 1)) {

                    if (arr[i][j + 1] == 1) {
                        arr[i][j + 1] = arr[i][j];
                    }
                }
                if ((arr[i][j] > 1) && (i != arr.length - 1)) {
                    if (arr[i + 1][j] == 1) {
                        arr[i + 1][j] = arr[i][j];
                    }
                }

            }
        }

        /* узнаем сколько ячеек у каждого айсберга */

        for (int x : arr1) {
            int count = 0;
            int element = x;
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    if (element == arr[i][j]) {
                        count++;
                    }
                }
            }
            arr2.add(count);
        }

        /* узнаем и выводим сколько айсбергов одного размера */

        arr2.stream().collect(Collectors.groupingBy((x) -> x, Collectors.counting()));
        arr2.stream()
                .collect(Collectors.groupingBy((x) -> x, Collectors.counting()))
                .entrySet()
                .forEach(entry -> {
                    arr3.add(entry.getKey() + " - " + entry.getValue() + "айсбергов" + "\n");
                });
        return arr3;
    }
}



