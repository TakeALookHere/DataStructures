package com.miskevich.datastructures.algorithms.lecture3;

import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.testng.Assert.*;

public class RandomizedQueueTest {

    @Test(dataProvider = "provideEmptyRandomizedQueue", dataProviderClass = RandomizedQueueDataProvider.class)
    public void testIsEmptyEmptyQueue(RandomizedQueue<String> randomizedQueue) throws Exception {
        assertTrue(randomizedQueue.isEmpty());
    }

    @Test(dataProvider = "provideNotEmptyRandomizedQueue", dataProviderClass = RandomizedQueueDataProvider.class)
    public void testIsEmptyNotEmptyQueue(RandomizedQueue<String> randomizedQueue) throws Exception {
        assertFalse(randomizedQueue.isEmpty());
    }

    @Test(dataProvider = "provideEmptyRandomizedQueue", dataProviderClass = RandomizedQueueDataProvider.class)
    public void testSizeEmptyQueue(RandomizedQueue<String> randomizedQueue) throws Exception {
        assertEquals(randomizedQueue.size(), 0);
    }

    @Test(dataProvider = "provideNotEmptyRandomizedQueue", dataProviderClass = RandomizedQueueDataProvider.class)
    public void testSizeNotEmptyQueue(RandomizedQueue<String> randomizedQueue) throws Exception {
        assertEquals(randomizedQueue.size(), 1);
    }

    @Test(dataProvider = "provideEmptyRandomizedQueue", dataProviderClass = RandomizedQueueDataProvider.class,
    expectedExceptionsMessageRegExp = "NULLs are prohibited to be added", expectedExceptions = IllegalArgumentException.class)
    public void testEnqueueNull(RandomizedQueue<String> randomizedQueue) throws Exception {
        randomizedQueue.enqueue(null);
        assertEquals(randomizedQueue.size(), 0);
    }

    @Test(dataProvider = "provideEmptyRandomizedQueue", dataProviderClass = RandomizedQueueDataProvider.class)
    public void testEnqueueEmptyQueue(RandomizedQueue<String> randomizedQueue) throws Exception {
        randomizedQueue.enqueue("str1");
        assertEquals(randomizedQueue.size(), 1);
        assertEquals(randomizedQueue.toString(), "[str1]");
    }

    @Test(dataProvider = "provideNotEmptyRandomizedQueue", dataProviderClass = RandomizedQueueDataProvider.class)
    public void testEnqueueNotEmptyQueue(RandomizedQueue<String> randomizedQueue) throws Exception {
        randomizedQueue.enqueue("str2");
        assertEquals(randomizedQueue.size(), 2);
        assertEquals(randomizedQueue.toString(), "[str1, str2]");
    }

    @Test(dataProvider = "provideNotEmptyRandomizedQueue", dataProviderClass = RandomizedQueueDataProvider.class)
    public void testEnqueueEnsureCapacity(RandomizedQueue<String> randomizedQueue) throws Exception {
        randomizedQueue.enqueue("str2");
        randomizedQueue.enqueue("str3");
        randomizedQueue.enqueue("str4");
        randomizedQueue.enqueue("str5");
        randomizedQueue.enqueue("str6");
        assertEquals(randomizedQueue.size(), 6);
        assertEquals(randomizedQueue.toString(), "[str1, str2, str3, str4, str5, str6]");
    }

    @Test(dataProvider = "provideEmptyRandomizedQueue", dataProviderClass = RandomizedQueueDataProvider.class,
    expectedExceptionsMessageRegExp = "Removing / sampling from the empty deque is prohibited", expectedExceptions = NoSuchElementException.class)
    public void testDequeueFromEmptyQueue(RandomizedQueue<String> randomizedQueue) throws Exception {
        randomizedQueue.dequeue();
    }

    @Test(dataProvider = "provideNotEmptyRandomizedQueue", dataProviderClass = RandomizedQueueDataProvider.class)
    public void testDequeue(RandomizedQueue<String> randomizedQueue) throws Exception {
        String removedItem = randomizedQueue.dequeue();
        assertEquals(randomizedQueue.size(), 0);
        assertEquals(removedItem, "str1");
    }

    @Test(dataProvider = "provideEmptyRandomizedQueue", dataProviderClass = RandomizedQueueDataProvider.class,
            expectedExceptionsMessageRegExp = "Removing / sampling from the empty deque is prohibited", expectedExceptions = NoSuchElementException.class)
    public void testSampleEmptyQueue(RandomizedQueue<String> randomizedQueue) throws Exception {
        randomizedQueue.sample();
    }

    @Test(dataProvider = "provideNotEmptyRandomizedQueue", dataProviderClass = RandomizedQueueDataProvider.class)
    public void testSample(RandomizedQueue<String> randomizedQueue) throws Exception {
        String sampledItem = randomizedQueue.sample();
        assertEquals(randomizedQueue.size(), 1);
        assertEquals(sampledItem, "str1");
    }

    @Test(dataProvider = "provideNotEmptyRandomizedQueue", dataProviderClass = RandomizedQueueDataProvider.class,
            expectedExceptionsMessageRegExp = "Please don't use this method", expectedExceptions = UnsupportedOperationException.class)
    public void testItrRemove(RandomizedQueue<String> randomizedQueue) {
        for (Iterator<String> iterator = randomizedQueue.iterator(); iterator.hasNext(); ) {
            iterator.remove();
        }
    }

    @Test(dataProvider = "provideEmptyRandomizedQueue", dataProviderClass = RandomizedQueueDataProvider.class)
    public void testItrHasNextEmptyQueue(RandomizedQueue<String> randomizedQueue) {
        Iterator<String> iterator = randomizedQueue.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test(dataProvider = "provideNotEmptyRandomizedQueue", dataProviderClass = RandomizedQueueDataProvider.class)
    public void testItrHasNextNotEmptyDeque(RandomizedQueue<String> randomizedQueue) {
        randomizedQueue.enqueue("str2");
        Iterator<String> iterator = randomizedQueue.iterator();
        for (int i = 0; i < randomizedQueue.size(); i++) {
            assertTrue(iterator.hasNext());
            iterator.next();
        }
        assertFalse(iterator.hasNext());
    }

    @Test(dataProvider = "provideNotEmptyRandomizedQueue", dataProviderClass = RandomizedQueueDataProvider.class)
    public void testItrNext(RandomizedQueue<String> randomizedQueue) {
        randomizedQueue.enqueue("str2");
        randomizedQueue.enqueue("str3");

        for (String aRandomizedQueue : randomizedQueue) {
            assertNotNull(aRandomizedQueue);
        }
    }
}