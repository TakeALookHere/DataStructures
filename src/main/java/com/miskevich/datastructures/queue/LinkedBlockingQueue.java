package com.miskevich.datastructures.queue;

import java.util.StringJoiner;

public class LinkedBlockingQueue<E> extends AbstractBlockingQueue<E>{

    private int size;
    private int capacity;
    private Node<E> head;
    private Node<E> tail;

    /**
     * @param capacity has to be > 0
     */
    public LinkedBlockingQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity of the queue can't be <= 0");
        }
        this.capacity = capacity;
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
        return head.value;
    }

    public synchronized E poll() {
        while (size == 0){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Node<E> first = head;
        E value = first.value;
        head = head.next;
        size--;
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
            Node<E> node = new Node<>(value);
            if(size == 0){
                head = tail = node;
            }else {
                tail.next = node;
                tail = node;
            }
            size++;
            notifyAll();
        }
    }

    public int size() {
        return size;
    }

    public String toString(){
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        Node<E> temp = head;
        while (temp != null){
            joiner.add(String.valueOf(temp.value));
            temp = temp.next;
        }

        return joiner.toString();
    }

    private static class Node<E>{
        E value;
        Node<E> next;

        private Node(E value){
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }
}
