package com.miskevich.datastructures;

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
        validateIndex(index);
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

    private void validateIndex(int index) {
        if (index < 0 || index >= size + 1) {
            String msg = "Incorrect index -> " + index +
                    ", index should be between 0 and " + size;
            throw new IllegalArgumentException(msg);
        }
    }

    public static void main(String[] args) {
        ArrayList<String> listEmpty = new ArrayList<String>();
        listEmpty.add("1");
        listEmpty.add("2");
        ArrayList<String> listFull = new ArrayList<String>();
        listFull.add("3");
        List<List<String>> listList = new ArrayList<List<String>>();
        listList.add(listEmpty);
        listList.add(listFull);
        Object[][] obj = new Object[][]{{listList, listList}};
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
        validateIndex(index);
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
        validateIndex(index);
        return (E) array[index];
    }

    public E remove(int index) {
        validateIndex(index);

        E removedValue = (E) array[index];
        System.arraycopy(array, index + 1, array, index, size - 1);
        size--;
        return removedValue;
    }
}
