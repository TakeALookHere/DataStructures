package com.miskevich.datastructures;

import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class HashMap<K, V> {

    private int size;
    private final int INITIAL_CAPACITY = 5;
    private final float LOAD_FACTOR = 0.75f;
    private List<Entry<K, V>>[] entries = new ArrayList[INITIAL_CAPACITY];

    public HashMap() {
        for (int i = 0; i < entries.length; i++) {
            if (entries[i] == null) {
                entries[i] = new ArrayList<>();
            }
        }
    }

    public int size(){
        return size;
    }

    public V put(K key, V value){
        ensureCapacity();
        Entry<K, V> entry = new Entry<>(key, value);
        int index = getIndex(key);
        List<Entry<K, V>> bucket = entries[index];

        for (Entry<K, V> temp : bucket) {
            if(temp.key.equals(key)){
                V oldValue = temp.value;
                temp.value = value;
                return oldValue;
            }
        }

        bucket.add(entry);
        size++;

        return entry.value;
    }

    public boolean containsKey(K key){
        int index = getIndex(key);
        List<Entry<K, V>> bucket = entries[index];
        for (Entry<K, V> entry : bucket) {
            if(entry.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsValue(V value){
        for (int i = 0; i < entries.length; i++) {
            if(entries[i].size() != 0){
                List<Entry<K, V>> bucket = entries[i];
                for (Entry entry : bucket) {
                    if(entry.value.equals(value)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public V get(K key){
        int index = getIndex(key);
        List<Entry<K, V>> bucket = entries[index];
        for (Entry<K, V> entry : bucket) {
            if(entry.key.equals(key)){
                return entry.value;
            }
        }
        String msg = "No element were found with key = " + key;
        throw new NoSuchElementException(msg);
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public V remove(K key){
        int index = getIndex(key);
        List<Entry<K, V>> bucket = entries[index];
        for (Iterator<Entry<K, V>> iterator = bucket.iterator(); iterator.hasNext();){
            Entry<K, V> entry = iterator.next();
            if(entry.key.equals(key)){
                iterator.remove();
                size--;
                return entry.value;
            }
        }
        String msg = "No element were found with key = " + key;
        throw new NoSuchElementException(msg);
    }

    public void clear(){
        for (int i = 0; i < entries.length; i++) {
            if(entries[i].size() != 0){
                entries[i] = new ArrayList<>();
            }
        }
        size = 0;
    }

    private int getIndex(K key) {
        return Math.abs(key.hashCode() % entries.length);
    }

    private void ensureCapacity(){
        int oldCapacity = entries.length;
        if(size > oldCapacity * LOAD_FACTOR){
            int newCapacity = oldCapacity * 2;
            List<Entry<K, V>> [] newBiggerEntries = new ArrayList[newCapacity];
            for (int i = 0; i < newBiggerEntries.length; i++) {
                if (newBiggerEntries[i] == null) {
                    newBiggerEntries[i] = new ArrayList<>();
                }
            }
            for (int i = 0; i < entries.length; i++) {
                for (int j = 0; j < entries[i].size(); j++) {
                    int newIndex = Math.abs(entries[i].get(j).key.hashCode() % newCapacity);
                    List<Entry<K, V>> newBucket = newBiggerEntries[newIndex];
                    Entry<K, V> entry = new Entry<>(entries[i].get(j).key, entries[i].get(j).value);
                    newBucket.add(entry);
                }
            }
            entries = newBiggerEntries;
        }
    }

    public String toString(){
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        for (int i = 0; i < entries.length; i++) {
            if(entries[i].size() != 0)
                joiner.add(String.valueOf(entries[i]));
        }

        return joiner.toString();
    }

    private static class Entry<K, V>{
        K key;
        V value;

        private Entry(K key, V value){
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
}
