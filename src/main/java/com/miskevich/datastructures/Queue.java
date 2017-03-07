package com.miskevich.datastructures;

public interface Queue<E> {
    /**
     * returns, but not remove the head of the queue if it's not empty
     * @return the head of the queue if it's not empty
     */
    E peek ();

    /**
     * removes the head of queue if it's not empty, returns removed element
     * @return the head of the queue if it's not empty
     */
    E poll();

    /**
     * Put element in the tail of the queue
     * null values for elements are prohibited
     * @param value element to add
     */
    void push(E value);

    /**
     * Get count of elements in the queue
     * @return count of elements in the queue
     */
    int size();
}
