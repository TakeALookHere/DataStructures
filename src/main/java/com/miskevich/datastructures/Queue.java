package com.miskevich.datastructures;

public interface Queue<E> {
    //return, but not remove the head of the queue
    //{@code null} if this queue is empty
    E peek ();
    //removes the head of queue, return removed element
    //{@code null} if this queue is empty
    E poll();
    void push(E value);
    int size();
}
