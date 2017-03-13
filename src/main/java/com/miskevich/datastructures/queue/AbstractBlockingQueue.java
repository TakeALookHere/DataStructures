package com.miskevich.datastructures.queue;

public abstract class AbstractBlockingQueue<E> implements Queue<E>{

    int size;

    public int size() {
        return size;
    }

    void checkNotNull(E value) {
        if(value == null){
            throw new NullPointerException("Null values are prohibited in the queue");
        }
    }
}
