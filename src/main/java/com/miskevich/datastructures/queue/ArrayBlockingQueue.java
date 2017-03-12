package com.miskevich.datastructures.queue;

import java.util.Iterator;
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
        if(takeIndex == capacity){
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

    public Iterator<E> iterator() {
        return new MyIterator();

    }

    public String toString(){
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        if(putIndex != 0){
            for (int i = putIndex; i < capacity; i++) {
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
            for (int i = putIndex; i < capacity; i++) {
                if (items[i] != null) {
                    joiner.add(String.valueOf(items[i]));
                }
            }
        }

        return joiner.toString();
    }

    private class MyIterator implements Iterator<E>{
        private int cursor;
        private int lastReturned = putIndex;

        public boolean hasNext() {
            synchronized (MyIterator.class) {
                return size != 0 && cursor < capacity;
            }
        }

        public E next() {
            synchronized (MyIterator.class) {
                E value;
                if(lastReturned != 0){
                    do{
                        value = items[lastReturned];
                        lastReturned++;
                        cursor++;
                        if(lastReturned == capacity){
                            lastReturned = 0;
                        }
                    }while (value == null);
                }else {
                    do{
                        value = items[lastReturned];
                        lastReturned++;
                        cursor++;
                    }while (value == null);
                }
                return value;
            }
        }

        public void remove() {
            synchronized (MyIterator.class){
                int current;
                if(lastReturned != 0){
                    current = (lastReturned - 1);
                }else {
                    current = capacity - 1;
                }
                items[current] = null;
                if((current == 0) && (takeIndex == 0)){
                    System.arraycopy(items, current + 1, items, current, capacity - lastReturned);
                }else if(current == 0){
                    items[0] = null;
                }else if(current == (capacity - 1)){
                    System.arraycopy(items, 0, items, capacity-1, 1);
                    if(items[0] != null){
                        items[0] = null;
                    }
                }else {
                    System.arraycopy(items, current + 1, items, current, capacity - lastReturned);
                    if((takeIndex == current) && (items[0] != null)){
                        System.arraycopy(items, 0, items, capacity-1, 1);
                        items[0] = null;
                    }
                }
                size--;
            }
        }
    }
}