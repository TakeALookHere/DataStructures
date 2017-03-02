package com.miskevich.datastructures;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.NoSuchElementException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class HashMapTest {

    private HashMap<Integer, String> map;

    @BeforeMethod
    public void initializeTestData(){
        this.map = new HashMap<>();
    }

    @Test
    public void testPutEmptyMap(){
        map.put(1, "str1");
        assertEquals(map.size(), 1);
        assertTrue(map.containsKey(1));
        assertTrue(map.containsValue("str1"));
    }

    @Test
    public void testPutNotEmptyMapNewValue(){
        map.put(1, "str1");
        map.put(2, "str2");
        assertEquals(map.size(), 2);
        assertTrue(map.containsKey(1));
        assertTrue(map.containsValue("str1"));
        assertTrue(map.containsKey(2));
        assertTrue(map.containsValue("str2"));
    }

    @Test
    public void testPutNotEmptyMapReplaceValue(){
        map.put(1, "str1");
        map.put(1, "str2");
        assertEquals(map.size(), 1);
        assertTrue(map.containsKey(1));
        assertFalse(map.containsValue("str1"));
        assertTrue(map.containsValue("str2"));
    }

    @Test
    public void testContainsKeyTrue(){
        map.put(1, "str1");
        assertTrue( map.containsKey(1));
    }

    @Test
    public void testContainsKeyFalse(){
        assertFalse(map.containsKey(1));
    }

    @Test
    public void testContainsValueTrue(){
        map.put(1, "str1");
        assertTrue(map.containsValue("str1"));
    }

    @Test
    public void testContainsValueFalse(){
        assertFalse(map.containsValue("str1"));
    }

    @Test
    public void testGet(){
        map.put(1, "str1");
        String actual = map.get(1);
        assertEquals(actual, "str1");
    }

    @Test(expectedExceptions = NoSuchElementException.class, expectedExceptionsMessageRegExp = "No element were found with key = 1")
    public void testGetEmptyMap(){
        map.get(1);
    }

    @Test(expectedExceptions = NoSuchElementException.class, expectedExceptionsMessageRegExp = "No element were found with key = 0")
    public void testGetNotEmptyMapElementDoesNotExist(){
        map.put(1, "str1");
        map.get(0);
    }

    @Test
    public void testIsEmptyTrue(){
        assertTrue(map.isEmpty());
    }

    @Test
    public void testIsEmptyFalse(){
        map.put(1, "str1");
        assertFalse(map.isEmpty());
    }

    @Test(expectedExceptions = NoSuchElementException.class, expectedExceptionsMessageRegExp = "No element were found with key = 0")
    public void testRemoveEmptyMap(){
        map.remove(0);
    }

    @Test
    public void testRemoveValidKey(){
        map.put(1, "str1");
        assertEquals(map.size(), 1);
        map.remove(1);
        assertEquals(map.size(), 0);
    }

    @Test(expectedExceptions = NoSuchElementException.class, expectedExceptionsMessageRegExp = "No element were found with key = 2")
    public void testRemoveInvalidKey(){
        map.put(1, "str1");
        assertEquals(map.size(), 1);
        map.remove(2);
        assertEquals(map.size(), 1);
        assertTrue(map.containsKey(1));
        assertTrue(map.containsValue("str1"));
    }

    @Test
    public void testClearEmptyMap(){
        map.clear();
        assertEquals(map.size(), 0);
    }

    @Test
    public void testClearNotEmptyMap(){
        map.put(1, "str1");
        map.put(2, "str2");
        assertEquals(map.size(), 2);
        map.clear();
        assertEquals(map.size(), 0);
    }

    @Test
    public void testEnsureCapacity(){
        map.put(1, "str1");
        map.put(2, "str2");
        map.put(3, "str3");
        map.put(4, "str4");
        assertEquals(map.size(), 4);
        map.put(5, "str5");
        assertEquals(map.size(), 5);
        assertTrue(map.containsKey(1));
        assertTrue(map.containsValue("str1"));
        assertTrue(map.containsKey(2));
        assertTrue(map.containsValue("str2"));
        assertTrue(map.containsKey(3));
        assertTrue(map.containsValue("str3"));
        assertTrue(map.containsKey(4));
        assertTrue(map.containsValue("str4"));
        assertTrue(map.containsKey(5));
        assertTrue(map.containsValue("str5"));
    }

}