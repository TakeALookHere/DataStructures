package com.miskevich.datastructures.algorithms.lecture3;

import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.testng.Assert.*;

public class DequeTest {

    @Test(dataProvider = "provideEmptyDeque", dataProviderClass = DequeDataProvider.class)
    public void testIsEmptyEmptyDeque(Deque<String> deque) throws Exception {
        assertTrue(deque.isEmpty());
    }

    @Test(dataProvider = "provideNotEmptyDeque", dataProviderClass = DequeDataProvider.class)
    public void testIsEmptyNotEmptyDeque(Deque<String> deque) throws Exception {
        assertFalse(deque.isEmpty());
    }

    @Test(dataProvider = "provideEmptyDeque", dataProviderClass = DequeDataProvider.class)
    public void testSizeEmptyDeque(Deque<String> deque) throws Exception {
        assertEquals(deque.size(), 0);
    }

    @Test(dataProvider = "provideNotEmptyDeque", dataProviderClass = DequeDataProvider.class)
    public void testSizeNotEmptyDeque(Deque<String> deque) throws Exception {
        assertEquals(deque.size(), 1);
    }

    @Test(dataProvider = "provideEmptyDeque", dataProviderClass = DequeDataProvider.class,
            expectedExceptionsMessageRegExp = "NULLs are prohibited to be added", expectedExceptions = IllegalArgumentException.class)
    public void testAddNullFirst(Deque<String> deque) throws Exception {
        deque.addFirst(null);
    }

    @Test(dataProvider = "provideEmptyDeque", dataProviderClass = DequeDataProvider.class,
            expectedExceptionsMessageRegExp = "NULLs are prohibited to be added", expectedExceptions = IllegalArgumentException.class)
    public void testAddNullLast(Deque<String> deque) throws Exception {
        deque.addLast(null);
    }

    @Test(dataProvider = "provideEmptyDeque", dataProviderClass = DequeDataProvider.class)
    public void testAddFirst(Deque<String> deque) throws Exception {
        deque.addFirst("str1");
        assertEquals(deque.size(), 1);
        deque.addFirst("str2");
        assertEquals(deque.size(), 2);
        assertEquals(deque.toString(), "[str2, str1]");
    }

    @Test(dataProvider = "provideNotEmptyDeque", dataProviderClass = DequeDataProvider.class)
    public void testAddLast(Deque<String> deque) throws Exception {
        deque.addLast("str2");
        assertEquals(deque.size(), 2);
        deque.addLast("str3");
        assertEquals(deque.size(), 3);
        assertEquals(deque.toString(), "[str1, str2, str3]");
    }

    @Test(dataProvider = "provideEmptyDeque", dataProviderClass = DequeDataProvider.class,
            expectedExceptionsMessageRegExp = "Removing from the empty deque is prohibited", expectedExceptions = NoSuchElementException.class)
    public void testRemoveFirstEmptyDeque(Deque<String> deque) throws Exception {
        deque.removeFirst();
    }

    @Test(dataProvider = "provideEmptyDeque", dataProviderClass = DequeDataProvider.class,
            expectedExceptionsMessageRegExp = "Removing from the empty deque is prohibited", expectedExceptions = NoSuchElementException.class)
    public void testRemoveLastEmptyDeque(Deque<String> deque) throws Exception {
        deque.removeLast();
    }

    @Test(dataProvider = "provideNotEmptyDeque", dataProviderClass = DequeDataProvider.class)
    public void testRemoveFirst(Deque<String> deque) throws Exception {
        deque.addFirst("str2");
        assertEquals(deque.toString(), "[str2, str1]");
        deque.removeFirst();
        assertEquals(deque.size(), 1);
        assertEquals(deque.toString(), "[str1]");
    }

    @Test(dataProvider = "provideNotEmptyDeque", dataProviderClass = DequeDataProvider.class)
    public void testRemoveLast(Deque<String> deque) throws Exception {
        deque.addLast("str2");
        assertEquals(deque.size(), 2);
        deque.removeLast();
        assertEquals(deque.size(), 1);
        assertEquals(deque.toString(), "[str1]");
    }

    @Test(dataProvider = "provideEmptyDeque", dataProviderClass = DequeDataProvider.class)
    public void testItrHasNextEmptyDeque(Deque<String> deque) {
        Iterator<String> iterator = deque.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test(dataProvider = "provideNotEmptyDeque", dataProviderClass = DequeDataProvider.class)
    public void testItrHasNextNotEmptyDeque(Deque<String> deque) {
        deque.addFirst("str2");
        Iterator<String> iterator = deque.iterator();
        for (int i = 0; i < deque.size(); i++) {
            assertTrue(iterator.hasNext());
            iterator.next();
        }
        assertFalse(iterator.hasNext());
    }

    @Test(dataProvider = "provideNotEmptyDeque", dataProviderClass = DequeDataProvider.class)
    public void testItrNext(Deque<String> deque) {
        deque.addFirst("str2");
        deque.addFirst("str3");

        int i = 0;
        for (Iterator<String> iterator = deque.iterator(); iterator.hasNext(); ) {
            switch (i) {
                case 0:
                    assertEquals(iterator.next(), "str3");
                    i++;
                    break;
                case 1:
                    assertEquals(iterator.next(), "str2");
                    i++;
                    break;
                case 2:
                    assertEquals(iterator.next(), "str1");
                    i++;
                    break;
            }
        }
    }

    @Test(dataProvider = "provideNotEmptyDeque", dataProviderClass = DequeDataProvider.class,
            expectedExceptionsMessageRegExp = "Please don't use this method", expectedExceptions = UnsupportedOperationException.class)
    public void testItrRemove(Deque<String> deque) {
        for (Iterator<String> iterator = deque.iterator(); iterator.hasNext(); ) {
            iterator.remove();
        }
    }
}