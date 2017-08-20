package com.miskevich.datastructures.list;

import java.util.Iterator;
import java.util.StringJoiner;

public class ArrayList<E> extends AbstractList<E> {

    private E[] array;
    private static final int INITIAL_CAPACITY = 5;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        array = (E[]) new Object[INITIAL_CAPACITY];
    }

    public void add(int index, E value) {
        validateElementIndex(index);
        ensureCapacity();

        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    private void ensureCapacity() {
        if (size == array.length) {
            @SuppressWarnings("unchecked")
            E[] newBiggerArray = (E[]) new Object[(int) Math.round(array.length * 1.5)];
            System.arraycopy(array, 0, newBiggerArray, 0, array.length);
            array = newBiggerArray;
        }
    }

    public int indexOf(E value) {
        if (value == null) {
            for (int i = 0; i < size; i++)
                if (array[i] == null)
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
            for (int i = size - 1; i >= 0; i--)
                if (array[i] == null)
                    return i;
        } else {
            for (int i = size - 1; i >= 0; i--)
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
        validatePositionIndex(index);
        return array[index];
    }

    public E remove(int index) {
        validatePositionIndex(index);

        E removedValue = array[index];
        System.arraycopy(array, index + 1, array, index, size - 1);
        size--;
        return removedValue;
    }

    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        for (int i = 0; i < size; i++) {
            joiner.add(String.valueOf(array[i]));
        }

        return joiner.toString();
    }

    public Iterator<E> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<E> {
        private int cursor;

        public boolean hasNext() {
            return cursor < size;
        }

        public E next() {
            cursor++;
            return array[cursor - 1];
        }

        public void remove() {
            ArrayList.this.remove(cursor - 1);
            cursor = cursor - 1;
        }
    }
}
