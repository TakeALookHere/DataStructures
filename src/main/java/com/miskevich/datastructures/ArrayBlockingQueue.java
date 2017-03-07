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
                //putIndex--;
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
            //items[countOfElementsInQueue] = value;
            items[putIndex] = value;
            putIndex++;
            countOfElementsInQueue++;
            if(putIndex >= capacity){
                putIndex = 0;
            }
            items.notifyAll();
        }
    }

    public static void main(String[] args) {
        Queue<String> queue = new ArrayBlockingQueue<>(5);
        queue.push("str0");
        queue.push("str1");
        queue.push("str2");
        queue.push("str3");
        queue.push("str4");
        System.out.println(queue);
        queue.poll();
        System.out.println(queue);
        queue.push("new");
        System.out.println(queue);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue);
        System.out.println(queue.poll());
        System.out.println(queue);
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
