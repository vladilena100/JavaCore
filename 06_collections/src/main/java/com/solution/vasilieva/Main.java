package com.solution.vasilieva;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        DefaultCollectionClass<Integer> defaultCollectionClass = new DefaultCollectionClass<Integer>();
        defaultCollectionClass.add(1);
        defaultCollectionClass.add(2);
        defaultCollectionClass.add(3);
        System.out.println(defaultCollectionClass.remove(2));
    }
}
