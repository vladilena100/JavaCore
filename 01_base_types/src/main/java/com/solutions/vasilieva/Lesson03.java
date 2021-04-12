package com.solutions.vasilieva;

import java.util.Arrays;

public class Lesson03 {
    public static void main(String[] args) {
        BaseTypes baseTypes = new BaseTypes();
        int[] arr = {1, 2, 6, 4, 32, 3};
        //System.out.println(baseTypes.toggleScientificNotation("0.1248724657"));
        System.out.print(Arrays.toString(baseTypes.sort(arr)));
        //System.out.print(baseTypes.arithmeticMean(arr));
        //System.out.print(baseTypes.format(3.52, "UA"));
        //System.out.print(baseTypes.plus("1.45", "2.093"));
        //System.out.print(baseTypes.minus("5.8", "2.3"));
        //System.out.print(baseTypes.mul("5.8", "2.3"));
        //System.out.print(baseTypes.div("10", "3"));
    }
}
