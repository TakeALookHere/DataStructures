package com.miskevich.datastructures.algorithms.lecture3;

import org.testng.annotations.DataProvider;

public class RandomizedQueueDataProvider {

    @DataProvider(name = "provideEmptyRandomizedQueue")
    public static Object[][] provideEmptyRandomizedQueue() {
        RandomizedQueue<String> randomizedQueue= new RandomizedQueue<>();

        return new Object[][] {
                { randomizedQueue }
        };
    }

    @DataProvider(name = "provideNotEmptyRandomizedQueue")
    public static Object[][] provideNotEmptyRandomizedQueue() {
        RandomizedQueue<String> randomizedQueue= new RandomizedQueue<>();
        randomizedQueue.enqueue("str1");

        return new Object[][] {
                { randomizedQueue }
        };
    }
}
