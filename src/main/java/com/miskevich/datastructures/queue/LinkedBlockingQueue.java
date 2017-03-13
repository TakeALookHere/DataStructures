package com.miskevich.datastructures.queue;

import java.util.Iterator;
import java.util.StringJoiner;

public class LinkedBlockingQueue<E> extends AbstractBlockingQueue<E>{

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

    public String toString(){
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        Node<E> temp = head;
        while (temp != null){
            joiner.add(String.valueOf(temp.value));
            temp = temp.next;
        }

        return joiner.toString();
    }

    public Iterator<E> iterator() {
        return new MyIterator();
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

    private class MyIterator implements Iterator<E>{
        private Node<E> currentNode = head;
        private int cursor;
        private Node<E> lastReturned;
        private Node<E> prev;

        public boolean hasNext() {
            synchronized (LinkedBlockingQueue.class){
                return cursor < size;
            }
        }

        public E next() {
            synchronized (LinkedBlockingQueue.class){
                E value = currentNode.value;
                prev = lastReturned;
                lastReturned = currentNode;
                currentNode = currentNode.next;
                cursor++;
                return value;
            }
        }

        public void remove() {
            synchronized (LinkedBlockingQueue.class){
                if(lastReturned == head){
                    head = currentNode;
                    lastReturned.next = null;
                    lastReturned.value = null;
                }else if(lastReturned.next == null){
                    prev.next = null;
                    tail = prev;
                    lastReturned.value = null;
                }else {
                    prev.next = currentNode;
                    lastReturned.next = null;
                    lastReturned.value = null;
                }
                cursor--;
                size--;
            }
        }
    }
}
