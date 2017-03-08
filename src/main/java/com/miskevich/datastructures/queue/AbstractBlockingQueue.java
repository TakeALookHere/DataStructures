package com.miskevich.datastructures.queue;

public abstract class AbstractBlockingQueue<E> implements Queue<E>{

    void checkNotNull(E value) {
        if(value == null){
            throw new NullPointerException("Null values are prohibited in the queue");
        }
    }
}
