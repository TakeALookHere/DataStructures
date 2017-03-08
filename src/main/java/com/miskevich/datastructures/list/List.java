package com.miskevich.datastructures.list;

public interface List<E> {

    int size();
    void add (E value);
    void add (int index, E value);
    int indexOf(E value);
    int lastIndexOf(E value);
    boolean contains(E value);
    E set(int index, E value);
    void clear();
    E get(int index);
    E remove(int index);
}
