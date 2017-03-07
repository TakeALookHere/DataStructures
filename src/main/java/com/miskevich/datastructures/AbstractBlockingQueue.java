package com.miskevich.datastructures;

public abstract class AbstractBlockingQueue<E> {

    void checkNotNull(E value) {
        if(value == null){
            throw new NullPointerException("Null values are prohibited in the queue");
        }
    }
}
