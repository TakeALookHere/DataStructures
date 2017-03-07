package com.miskevich.datastructures;

import java.util.StringJoiner;

public class ArrayBlockingQueue<E> extends AbstractBlockingQueue<E> implements Queue<E> {

    private int countOfElementsInQueue;
    private final E[] items;
    private int takeIndex;
    private int capacity;

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
            if(countOfElementsInQueue > 0){
                return items[takeIndex];
            }else {
                return null;
            }
        }
    }

    public E poll() {
        synchronized (items){
            if(countOfElementsInQueue > 0){
                E value = items[takeIndex];
                items[takeIndex] = null;
                takeIndex++;
                countOfElementsInQueue--;
                if(takeIndex >= capacity){
                    takeIndex = 0;
                }
                return value;
            }else {
                return null;
            }
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
            items[countOfElementsInQueue] = value;
            countOfElementsInQueue++;
            items.notifyAll();
        }
    }

    public int size() {
        return countOfElementsInQueue;
    }


    public String toString(){
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        for (int i = 0; i < items.length; i++) {
            if(items[i] != null) {
                joiner.add(String.valueOf(items[i]));
            }
        }
        return joiner.toString();
    }
}
