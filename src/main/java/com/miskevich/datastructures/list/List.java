package com.miskevich.datastructures.list;

import java.util.Iterator;

public interface List<E> extends Iterable<E>{

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
    Iterator<E> iterator();
}
