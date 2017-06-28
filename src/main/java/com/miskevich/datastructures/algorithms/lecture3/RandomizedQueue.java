package com.miskevich.datastructures.algorithms.lecture3;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private static final int INITIAL_CAPACITY = 5;
    private Item[] items;
    private int size;

    public RandomizedQueue() {
        items = (Item[]) new Object[INITIAL_CAPACITY];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("NULLs are prohibited to be added");
        }
        ensureCapacity();
        items[size] = item;
        size++;
    }

    private void ensureCapacity() {
        if (size == items.length) {
            Item[] newBiggerItems = (Item[]) new Object[items.length * 2];
            System.arraycopy(items, 0, newBiggerItems, 0, items.length);
            items = newBiggerItems;
        }
    }

    private void emptyQueueCheck() {
        if (isEmpty()) {
            throw new NoSuchElementException("Removing / sampling from the empty deque is prohibited");
        }
    }

    public Item dequeue() {
        emptyQueueCheck();

        int randomIndexToRemove = StdRandom.uniform(size);
        Item removedItem = items[randomIndexToRemove];

        if (randomIndexToRemove == size - 1) {
            items[randomIndexToRemove] = null;
        } else {
            items[randomIndexToRemove] = items[size - 1];
            items[size - 1] = null;
        }

        size--;
        shrinkCapacity();
        return removedItem;
    }

    private void shrinkCapacity() {
        if (size > INITIAL_CAPACITY && size == items.length / 4) {
            Item[] newSmallerItems = (Item[]) new Object[items.length / 2];
            System.arraycopy(items, 0, newSmallerItems, 0, size);
            items = newSmallerItems;
        }
    }

    public Item sample() {
        emptyQueueCheck();
        Item randomItem;
        do {
            int randomIndex = StdRandom.uniform(items.length);
            randomItem = items[randomIndex];
        } while (randomItem == null);

        return randomItem;
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private int[] randomOrder = new int[size];
        private int cursor;

        public RandomizedQueueIterator() {
            randomOrder = createRandomOrder();
        }

        private int[] createRandomOrder() {
            int randomIndexCounter = 0;
            for (int i = 0; i < size; i++) {
                boolean isCheckFail = false;
                int randomIndex = StdRandom.uniform(size);
                for (int j = 0; j < randomIndexCounter; j++) {
                    if (randomOrder[j] == randomIndex) {
                        i--;
                        isCheckFail = true;
                    }
                }
                if (!isCheckFail) {
                    randomOrder[i] = randomIndex;
                    randomIndexCounter++;
                }
            }
            return randomOrder;
        }

        public boolean hasNext() {
            return size != 0 && cursor < size;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more items to return");
            }
            int itemIndex = randomOrder[cursor];
            cursor++;
            return items[itemIndex];
        }

        public void remove() {
            throw new UnsupportedOperationException("Please don't use this method");
        }
    }

    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(", ", "[", "]");
        for (int i = 0; i < size; i++) {
            stringJoiner.add(String.valueOf(items[i]));
        }
        return stringJoiner.toString();
    }
}
