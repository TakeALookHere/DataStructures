package com.miskevich.datastructures;

public abstract class AbstractList{

    void validateElementIndex(int index, int size) {
        if (index < 0 || index > size) {
            String msg = "Incorrect element index -> " + index +
                    ", index should be between 0 and " + size + " inclusive";
            throw new IllegalArgumentException(msg);
        }
    }

    void validatePositionIndex(int index, int size) {
        if(index == 0 && size == 0){
            String msg = "Current operation is prohibited for empty list: index = 0, size = 0";
            throw new IndexOutOfBoundsException(msg);
        }else if (index < 0 || index >= size) {
            String msg = "Incorrect position index -> " + index +
                    ", index should be between 0 and " + size + " exclusive";
            throw new IllegalArgumentException(msg);
        }
    }
}
