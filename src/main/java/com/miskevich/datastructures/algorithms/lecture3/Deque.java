package com.miskevich.datastructures.algorithms.lecture3;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

public class Deque<Item> implements Iterable<Item> {

    private int size;
    private Node<Item> first;
    private Node<Item> last;

    public Deque() {
        first = last = null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void checkNulls(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("NULLs are prohibited to be added");
        }
    }

    public void addFirst(Item item) {
        checkNulls(item);
        Node<Item> node = new Node<>(item);
        if (size == 0) {
            first = last = node;
        } else {
            Node<Item> oldFirst = first;
            first = node;
            first.next = oldFirst;
            oldFirst.prev = first;
        }
        size++;
    }

    public void addLast(Item item) {
        checkNulls(item);
        Node<Item> node = new Node<>(item);
        if (size == 0) {
            first = last = node;
        } else {
            Node<Item> oldLast = last;
            last = node;
            last.next = null;
            oldLast.next = node;
            node.prev = oldLast;
        }
        size++;
    }

    private void checkRemoveFromEmpty() {
        if (size == 0) {
            throw new NoSuchElementException("Removing from the empty deque is prohibited");
        }
    }

    public Item removeFirst() {
        checkRemoveFromEmpty();
        Node<Item> oldFirst = first;
        first = first.next;
        oldFirst.next = null;
        //first.prev = null;
        size--;
        return oldFirst.item;
    }

    public Item removeLast() {
        checkRemoveFromEmpty();
        Node<Item> oldLast = last;
        last = last.prev;
        //last.next = null;
        size--;
        return oldLast.item;
    }

    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        Node<Item> temp = first;
        while (temp != null) {
            joiner.add(String.valueOf(temp.item));
            temp = temp.next;
        }

        return joiner.toString();
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> prev;

        public Node(Item item) {
            this.item = item;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "item=" + item +
                    '}';
        }
    }

    private class DequeIterator implements Iterator<Item> {

        private Node<Item> currentItem;

        public boolean hasNext() {
            return size != 0 && currentItem != last;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements to return");
            }
            if (currentItem == null) {
                currentItem = first;
            } else {
                currentItem = currentItem.next;
            }
            return currentItem.item;
        }

        public void remove() {
            throw new UnsupportedOperationException("Please don't use this method");
        }
    }
}
