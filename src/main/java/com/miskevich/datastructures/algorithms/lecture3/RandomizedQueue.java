package com.miskevich.datastructures.algorithms.lecture3;

import edu.princeton.cs.introcs.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private final int INITIAL_CAPACITY = 5;
    private Item[] items;
    private int size;

    @SuppressWarnings("unchecked")
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
            @SuppressWarnings("unchecked")
            Item[] newBiggerItems = (Item[]) new Object[(int) Math.round(items.length * 1.5)];
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
        Item removedItem;
        do {
            int randomIndexToRemove = StdRandom.uniform(items.length);
            removedItem = items[randomIndexToRemove];
            items[randomIndexToRemove] = null;
        } while (removedItem == null);

        size--;
        shrinkCapacity();
        return removedItem;
    }

    private void shrinkCapacity() {
        if (size > INITIAL_CAPACITY && size == items.length / 4) {
            @SuppressWarnings("unchecked")
            Item[] newSmallerItems = (Item[]) new Object[Math.round(items.length / 2)];
            System.arraycopy(items, 0, newSmallerItems, 0, items.length);
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
