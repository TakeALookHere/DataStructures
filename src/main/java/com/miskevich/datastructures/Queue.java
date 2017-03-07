package com.miskevich.datastructures;

public interface Queue<E> {
    /**
     * returns, but not remove the head of the queue
     * or returns {@code null} if the queue is empty
     * @return the head of the queue, or {@code null} if the queue is empty
     */
    E peek ();

    /**
     * removes the head of queue, returns removed element
     * or returns {@code null} if the queue is empty
     * @return the head of the queue, or {@code null} if this queue is empty
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
