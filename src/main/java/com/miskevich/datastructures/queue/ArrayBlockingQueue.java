package com.miskevich.datastructures.queue;

import java.util.StringJoiner;

public class ArrayBlockingQueue<E> extends AbstractBlockingQueue<E>{

    private int size;
    private E[] items;
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

    public synchronized E peek() {
        while (size == 0){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        notifyAll();
        return items[takeIndex];
    }

    public synchronized E poll() {
        while (size == 0){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        E value = items[takeIndex];
        items[takeIndex] = null;
        takeIndex++;
        size--;
        if(takeIndex >= capacity){
            takeIndex = 0;
        }
        notifyAll();
        return value;
    }

    public void push(E value) {
        checkNotNull(value);
        synchronized (this){
            while (size == capacity){
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            items[putIndex] = value;
            putIndex++;
            size++;
            if(putIndex == capacity){
                putIndex = 0;
            }
            notifyAll();
        }
    }

    public int size() {
        return size;
    }

    public String toString(){
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        if(putIndex != 0){
            for (int i = putIndex; i < size; i++) {
                if (items[i] != null) {
                    joiner.add(String.valueOf(items[i]));
                }
            }
            for (int i = 0; i < putIndex; i++) {
                if (items[i] != null) {
                    joiner.add(String.valueOf(items[i]));
                }
            }
        }else {
            for (int i = putIndex; i < size; i++) {
                if (items[i] != null) {
                    joiner.add(String.valueOf(items[i]));
                }
            }
        }

        return joiner.toString();
    }
}