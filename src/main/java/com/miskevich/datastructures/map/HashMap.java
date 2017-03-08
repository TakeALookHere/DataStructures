package com.miskevich.datastructures.map;

import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class HashMap<K, V> {

    private int size;
    private final int INITIAL_CAPACITY = 5;
    private final float LOAD_FACTOR = 0.75f;
    @SuppressWarnings("unchecked")
    private List<Entry<K, V>>[] entries = new ArrayList[INITIAL_CAPACITY];

    public HashMap() {
        for (int i = 0; i < entries.length; i++) {
            entries[i] = new ArrayList<>();
        }
    }

    public int size(){
        return size;
    }

    public V put(K key, V value){
        extendCapacityCheck();
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
        throw new NoSuchElementException("No element were found with key = " + key);
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

    private void extendCapacityCheck(){
        int oldCapacity = entries.length;
        if(size > oldCapacity * LOAD_FACTOR){
            int newCapacity = oldCapacity * 2;
            @SuppressWarnings("unchecked")
            List<Entry<K, V>> [] newBiggerEntries = new ArrayList[newCapacity];
            for (int i = 0; i < newBiggerEntries.length; i++) {
                newBiggerEntries[i] = new ArrayList<>();
            }

            List<Entry<K, V>>[] oldEntries = entries;
            entries = newBiggerEntries;
            size = 0;

            for (List<Entry<K, V>> oldEntriesList : oldEntries) {
                for (Entry<K, V> oldEntry : oldEntriesList) {
                    put(oldEntry.key, oldEntry.value);
                }
            }
        }
    }

    public String toString(){
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        for (int i = 0; i < entries.length; i++) {
            if(!entries[i].isEmpty())
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