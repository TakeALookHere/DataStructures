package com.miskevich.datastructures.algorithms.lecture3;

import org.testng.annotations.DataProvider;

public class DequeDataProvider {

    @DataProvider(name = "provideEmptyDeque")
    public static Object[][] provideEmptyDeque() {
        Deque<String> deque= new Deque<>();

        return new Object[][] {
                { deque }
        };
    }

    @DataProvider(name = "provideNotEmptyDeque")
    public static Object[][] provideNotEmptyDeque() {
        Deque<String> deque= new Deque<>();
        deque.addFirst("str1");

        return new Object[][] {
                { deque }
        };
    }
}
