package com.miskevich.datastructures;

import java.util.StringJoiner;

public class ArrayBlockingQueue<E> extends AbstractBlockingQueue<E> implements Queue<E> {

    private int countOfElementsInQueue;
    private final E[] items;
    private int takeIndex;
    private int putIndex;
    private int capacity;

    /**
     * @param capacity has to be > 0
     */
    @SuppressWarnings("unchecked")
    public ArrayBlockingQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity of the queue can't be <= 0");
        }
        this.capacity = capacity;
        this.items = (E[]) new Object[capacity];
    }

    public E peek() {
        synchronized (items){
            while (countOfElementsInQueue == 0){
                try {
                    items.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            items.notifyAll();
            return items[takeIndex];
        }
    }

    public E poll() {
        synchronized (items){
            while (countOfElementsInQueue == 0){
                try {
                    items.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            E value = items[takeIndex];
            items[takeIndex] = null;
            takeIndex++;
            countOfElementsInQueue--;
            if(takeIndex >= capacity){
                takeIndex = 0;
            }
            items.notifyAll();
            return value;
        }
    }

    public void push(E value) {
        checkNotNull(value);
        synchronized (items){
            while (countOfElementsInQueue == capacity){
                try {
                    items.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            items[putIndex] = value;
            putIndex++;
            countOfElementsInQueue++;
            if(putIndex >= capacity){
                putIndex = 0;
            }
            items.notifyAll();
        }
    }

    public int size() {
        return countOfElementsInQueue;
    }


    public String toString(){
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        for (E item : items) {
            if (item != null) {
                joiner.add(String.valueOf(item));
            }
        }
        return joiner.toString();
    }
}