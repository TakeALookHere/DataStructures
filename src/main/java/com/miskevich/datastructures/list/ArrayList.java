package com.miskevich.datastructures.list;

import java.util.StringJoiner;

public class ArrayList<E> extends AbstractList implements List<E> {

    private E[] array;
    private int size;
    private static final int INITIAL_CAPACITY = 5;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        array = (E[])new Object[INITIAL_CAPACITY];
    }

    public int size() {
        return size;
    }

    public void add(E value) {
        add(size, value);
    }

    public void add(int index, E value) {
        validateElementIndex(index, size);
        ensureCapacity();

        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    private void ensureCapacity() {
        if(size == array.length){
            @SuppressWarnings("unchecked")
            E[] newBiggerArray = (E[]) new Object[(int) Math.round (array.length * 1.5)];
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
        validatePositionIndex(index, size);
        E oldValue = array[index];
        array[index] = value;
        return oldValue;
    }

    public void clear() {
        for (int i = 0; i < size; i++)
            array[i] = null;

        size = 0;
    }

    public E get(int index) {
        validatePositionIndex(index, size);
        return array[index];
    }

    public E remove(int index) {
        validatePositionIndex(index, size);

        E removedValue = array[index];
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
}