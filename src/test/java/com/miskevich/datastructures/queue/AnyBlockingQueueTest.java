package com.miskevich.datastructures.queue;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class AnyBlockingQueueTest {

    @DataProvider(name = "provideQueues")
    public Object[][] provideData() {
        Queue<String> blockingQueueArray = new ArrayBlockingQueue<>(5);
        Queue<String> blockingQueueLinked = new LinkedBlockingQueue<>(5);

        return new Object[][] {
                { blockingQueueArray },
                { blockingQueueLinked }
        };
    }

    @Test(dataProvider = "provideQueues")
    public void testPeek(Queue<String> blockingQueue){
        blockingQueue.push("str0");
        blockingQueue.push("str1");
        assertEquals(blockingQueue.peek(), "str0");
        assertEquals(blockingQueue.size(), 2);
    }

    @Test(dataProvider = "provideQueues")
    public void testPoll(Queue<String> blockingQueue){
        blockingQueue.push("str0");
        blockingQueue.push("str1");
        assertEquals(blockingQueue.poll(), "str0");
        assertEquals(blockingQueue.size(), 1);
        assertEquals(String.valueOf(blockingQueue), "[str1]");
    }

    @Test(dataProvider = "provideQueues")
    public void testPushEmptyQueue(Queue<String> blockingQueue){
        blockingQueue.push("str0");
        assertEquals(blockingQueue.size(), 1);
        assertEquals(String.valueOf(blockingQueue), "[str0]");
    }

    @Test(dataProvider = "provideQueues")
    public void testPushNotEmptyQueue(Queue<String> blockingQueue){
        blockingQueue.push("str0");
        blockingQueue.push("str1");
        assertEquals(blockingQueue.size(), 2);
        assertEquals(String.valueOf(blockingQueue), "[str0, str1]");
    }

    @Test(expectedExceptions = NullPointerException.class, expectedExceptionsMessageRegExp = "Null values are prohibited in the queue",
            dataProvider = "provideQueues")
    public void testPushNull(Queue<String> blockingQueue){
        blockingQueue.push(null);
    }

    @Test(dataProvider = "provideQueues")
    public void testSizeEmptyQueue(Queue<String> blockingQueue){
        assertEquals(blockingQueue.size(), 0);
    }

    @Test(dataProvider = "provideQueues")
    public void testSizeNotEmptyQueue(Queue<String> blockingQueue){
        blockingQueue.push("str0");
        assertEquals(blockingQueue.size(), 1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Capacity of the queue can't be <= 0")
    public void testInvalidCapacityArrayQueue(){
        new ArrayBlockingQueue<>(0);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Capacity of the queue can't be <= 0")
    public void testInvalidCapacityLinkedQueue(){
        new LinkedBlockingQueue<>(-1);
    }

    @Test(dataProvider = "provideQueues")
    public void testPushToCorrectIndexAfterPoll(Queue<String> blockingQueue){
        blockingQueue.push("str0");
        blockingQueue.push("str1");
        blockingQueue.push("str2");
        blockingQueue.push("str3");
        blockingQueue.push("str4");
        assertEquals(blockingQueue.size(), 5);
        assertEquals(String.valueOf(blockingQueue), "[str0, str1, str2, str3, str4]");
        assertEquals(blockingQueue.poll(), "str0");
        assertEquals(blockingQueue.size(), 4);
        blockingQueue.push("new");
        assertEquals(blockingQueue.size(), 5);
        assertEquals(String.valueOf(blockingQueue), "[str1, str2, str3, str4, new]");
        assertEquals(blockingQueue.poll(), "str1");
        assertEquals(blockingQueue.size(), 4);
        assertEquals(blockingQueue.poll(), "str2");
        assertEquals(blockingQueue.size(), 3);
        assertEquals(blockingQueue.poll(), "str3");
        assertEquals(blockingQueue.size(), 2);
        assertEquals(blockingQueue.poll(), "str4");
        assertEquals(blockingQueue.size(), 1);
        assertEquals(blockingQueue.poll(), "new");
        assertEquals(blockingQueue.size(), 0);
    }

}