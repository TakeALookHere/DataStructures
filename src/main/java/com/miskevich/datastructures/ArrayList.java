package com.miskevich.datastructures;

import java.util.StringJoiner;

public class ArrayList<E> implements List<E> {

    private Object[] array;
    private int size;
    private static final int INITIAL_CAPACITY = 5;
    public ArrayList() {
        array = new Object[INITIAL_CAPACITY];
    }


    public int size() {
        return size;
    }

    public void add(E value) {
        ensureCapacity();
        array[size] = value;
        size++;
    }

    public void add(int index, E value) {
        validateElementIndex(index);
        ensureCapacity();

        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    private void ensureCapacity() {
        if (size == INITIAL_CAPACITY){
            Object[] newBiggerArray = new Object[(int) Math.round (INITIAL_CAPACITY * 1.5)];
            System.arraycopy(array, 0, newBiggerArray, 0, array.length);
            array = newBiggerArray;
        }
    }

    public int indexOf(E value) {
        if (value == null) {
            for (int i = 0; i < size; i++)
                if (array[i]==null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (value.equals(array[i]))
                    return i;
        }
        return -1;
    }

    public int lastIndexOf(E value) {
        if (value == null) {
            for (int i = size-1; i >= 0; i--)
                if (array[i]==null)
                    return i;
        } else {
            for (int i = size-1; i >= 0; i--)
                if (value.equals(array[i]))
                    return i;
        }
        return -1;
    }

    public boolean contains(E value) {
        return indexOf(value) >= 0;
    }

    public E set(int index, E value) {
        validatePositionIndex(index);
        E oldValue = (E) array[index];
        array[index] = value;
        return oldValue;
    }

    public void clear() {
        for (int i = 0; i < size; i++)
            array[i] = null;

        size = 0;
    }

    public E get(int index) {
        validatePositionIndex(index);
        return (E) array[index];
    }

    public E remove(int index) {
        validatePositionIndex(index);

        E removedValue = (E) array[index];
        System.arraycopy(array, index + 1, array, index, size - 1);
        size--;
        return removedValue;
    }

    public String toString(){
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        for (int i = 0; i < size; i++) {
            joiner.add(String.valueOf(array[i]));
        }

        return joiner.toString();
    }

    private void validateElementIndex(int index) {
        if (index < 0 || index > size) {
            String msg = "Incorrect element index -> " + index +
                    ", index should be between 0 and " + size;
            throw new IllegalArgumentException(msg);
        }
    }

    private void validatePositionIndex(int index) {
        if(index == 0 && size == 0){
            String msg = "Current operation is prohibited for empty list: index = 0, size = 0";
            throw new IndexOutOfBoundsException(msg);
        }else if (index < 0 || index >= size) {
            String msg = "Incorrect position index -> " + index +
                    ", index should be between 0 and " + (size -1);
            throw new IllegalArgumentException(msg);
        }
    }
}
