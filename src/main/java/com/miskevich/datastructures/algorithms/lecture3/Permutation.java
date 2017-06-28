package com.miskevich.datastructures.algorithms.lecture3;

import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {

        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            randomizedQueue.enqueue(StdIn.readString());

            if (randomizedQueue.size() == k) {
                for (String s : randomizedQueue) {
                    System.out.println(s);
                }
            }
        }
    }
}
