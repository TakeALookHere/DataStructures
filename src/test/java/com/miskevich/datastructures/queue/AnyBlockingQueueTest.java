package com.miskevich.datastructures.queue;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;

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
        assertEquals(blockingQueue.poll(), "str1");
    }

    @Test(dataProvider = "provideQueues")
    public void testPushEmptyQueue(Queue<String> blockingQueue){
        blockingQueue.push("str0");
        assertEquals(blockingQueue.size(), 1);
        assertEquals(blockingQueue.poll(), "str0");
    }

    @Test(dataProvider = "provideQueues")
    public void testPushNotEmptyQueue(Queue<String> blockingQueue){
        blockingQueue.push("str0");
        blockingQueue.push("str1");
        assertEquals(blockingQueue.size(), 2);
        assertEquals(blockingQueue.poll(), "str0");
        assertEquals(blockingQueue.poll(), "str1");
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
        assertEquals(blockingQueue.poll(), "str0");
        assertEquals(blockingQueue.size(), 4);
        blockingQueue.push("new");
        assertEquals(blockingQueue.size(), 5);
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

    @Test(dataProvider = "provideQueues")
    public void testItrHasNextEmptyQueue(Queue<String> blockingQueue){
        assertEquals(blockingQueue.size(), 0);
        Iterator<String> iterator = blockingQueue.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test(dataProvider = "provideQueues")
    public void testItrHasNextFullQueue(Queue<String> blockingQueue){
        blockingQueue.push("str0");
        blockingQueue.push("str1");
        blockingQueue.push("str2");
        blockingQueue.push("str3");
        blockingQueue.push("str4");
        assertEquals(blockingQueue.size(), 5);
        Iterator<String> iterator = blockingQueue.iterator();
        for (int i = 0; i < blockingQueue.size(); i++) {
            assertTrue(iterator.hasNext());
            iterator.next();
        }
        assertFalse(iterator.hasNext());
    }

    @Test(dataProvider = "provideQueues")
    public void testItrHasNextEmptyAtBeginQueue(Queue<String> blockingQueue){
        blockingQueue.push("str0");
        blockingQueue.push("str1");
        blockingQueue.push("str2");
        blockingQueue.push("str3");
        blockingQueue.push("str4");
        assertEquals(blockingQueue.size(), 5);
        blockingQueue.poll();
        assertEquals(blockingQueue.size(), 4);
        Iterator<String> iterator = blockingQueue.iterator();
        for (int i = 0; i < blockingQueue.size(); i++) {
            assertTrue(iterator.hasNext());
            iterator.next();
        }
        assertFalse(iterator.hasNext());
    }

    @Test(dataProvider = "provideQueues")
    public void testItrHasNextRepopulatedQueue(Queue<String> blockingQueue){
        blockingQueue.push("str0");
        blockingQueue.push("str1");
        blockingQueue.push("str2");
        blockingQueue.push("str3");
        blockingQueue.push("str4");
        assertEquals(blockingQueue.size(), 5);
        blockingQueue.poll();
        assertEquals(blockingQueue.size(), 4);
        blockingQueue.push("new");
        blockingQueue.poll();
        blockingQueue.poll();
        assertEquals(blockingQueue.size(), 3);
        Iterator<String> iterator = blockingQueue.iterator();
        for (int i = 0; i < blockingQueue.size(); i++) {
            assertTrue(iterator.hasNext());
            iterator.next();
        }
        assertFalse(iterator.hasNext());
    }

    @Test(dataProvider = "provideQueues")
    public void testItrNext(Queue<String> blockingQueue){
        blockingQueue.push("str0");
        blockingQueue.push("str1");
        blockingQueue.push("str2");
        int i = 0;
        for (Iterator<String> iterator = blockingQueue.iterator(); iterator.hasNext();){
            switch (i){
                case 0:
                    assertEquals(iterator.next(), "str0");
                    i++;
                    break;
                case 1:
                    assertEquals(iterator.next(), "str1");
                    i++;
                    break;
                case 2:
                    assertEquals(iterator.next(), "str2");
                    break;
            }

        }
    }

    @Test(dataProvider = "provideQueues")
    public void testItrNextRepopulatedQueue(Queue<String> blockingQueue){
        blockingQueue.push("str0");
        blockingQueue.push("str1");
        blockingQueue.push("str2");
        blockingQueue.push("str3");
        blockingQueue.push("str4");
        assertEquals(blockingQueue.size(), 5);
        blockingQueue.poll();
        assertEquals(blockingQueue.size(), 4);
        blockingQueue.push("new");
        blockingQueue.poll();
        blockingQueue.poll();
        assertEquals(blockingQueue.size(), 3);
        int i = 0;
        for (Iterator<String> iterator = blockingQueue.iterator(); iterator.hasNext();){
            switch (i){
                case 0:
                    assertEquals(iterator.next(), "str3");
                    i++;
                    break;
                case 1:
                    assertEquals(iterator.next(), "str4");
                    i++;
                    break;
                case 2:
                    assertEquals(iterator.next(), "new");
                    break;
            }

        }
    }

    @Test(dataProvider = "provideQueues")
    public void testItrRemoveBegin(Queue<String> blockingQueue){
        blockingQueue.push("str0");
        blockingQueue.push("str1");
        blockingQueue.push("str2");
        assertEquals(blockingQueue.size(), 3);
        for (Iterator<String> iterator = blockingQueue.iterator(); iterator.hasNext();){
            String next = iterator.next();
            if(next.equals("str0")){
                iterator.remove();
            }
        }
        assertEquals(blockingQueue.size(), 2);
        assertEquals(blockingQueue.poll(), "str1");
        assertEquals(blockingQueue.poll(), "str2");
    }

    @Test(dataProvider = "provideQueues")
    public void testItrRemoveRepopulatedBegin(Queue<String> blockingQueue){
        blockingQueue.push("str0");
        blockingQueue.push("str1");
        blockingQueue.push("str2");
        blockingQueue.push("str3");
        blockingQueue.push("str4");
        blockingQueue.poll();
        blockingQueue.push("new");
        blockingQueue.poll();
        blockingQueue.poll();
        assertEquals(blockingQueue.size(), 3);
        for (Iterator<String> iterator = blockingQueue.iterator(); iterator.hasNext();){
            String next = iterator.next();
            if(next.equals("str3")){
                iterator.remove();
            }
        }
        assertEquals(blockingQueue.size(), 2);
        assertEquals(blockingQueue.poll(), "str4");
        assertEquals(blockingQueue.poll(), "new");
    }

    @Test(dataProvider = "provideQueues")
    public void testItrRemoveCenter(Queue<String> blockingQueue){
        blockingQueue.push("str0");
        blockingQueue.push("str1");
        blockingQueue.push("str2");
        assertEquals(blockingQueue.size(), 3);
        for (Iterator<String> iterator = blockingQueue.iterator(); iterator.hasNext();){
            String next = iterator.next();
            if(next.equals("str1")){
                iterator.remove();
            }
        }
        assertEquals(blockingQueue.size(), 2);
        assertEquals(blockingQueue.poll(), "str0");
        assertEquals(blockingQueue.poll(), "str2");
    }

    @Test(dataProvider = "provideQueues")
    public void testItrRemoveRepopulatedCenter(Queue<String> blockingQueue){
        blockingQueue.push("str0");
        blockingQueue.push("str1");
        blockingQueue.push("str2");
        blockingQueue.push("str3");
        blockingQueue.push("str4");
        blockingQueue.poll();
        blockingQueue.push("new");
        blockingQueue.poll();
        blockingQueue.poll();
        assertEquals(blockingQueue.size(), 3);
        for (Iterator<String> iterator = blockingQueue.iterator(); iterator.hasNext();){
            String next = iterator.next();
            if(next.equals("str4")){
                iterator.remove();
            }
        }
        assertEquals(blockingQueue.size(), 2);
        assertEquals(blockingQueue.poll(), "str3");
        assertEquals(blockingQueue.poll(), "new");
    }

    @Test(dataProvider = "provideQueues")
    public void testItrRemoveEnd(Queue<String> blockingQueue){
        blockingQueue.push("str0");
        blockingQueue.push("str1");
        blockingQueue.push("str2");
        assertEquals(blockingQueue.size(), 3);
        for (Iterator<String> iterator = blockingQueue.iterator(); iterator.hasNext();){
            String next = iterator.next();
            if(next.equals("str2")){
                iterator.remove();
            }
        }
        assertEquals(blockingQueue.size(), 2);
        assertEquals(blockingQueue.poll(), "str0");
        assertEquals(blockingQueue.poll(), "str1");
    }

    @Test(dataProvider = "provideQueues")
    public void testItrRemoveRepopulatedEnd(Queue<String> blockingQueue){
        blockingQueue.push("str0");
        blockingQueue.push("str1");
        blockingQueue.push("str2");
        blockingQueue.push("str3");
        blockingQueue.push("str4");
        blockingQueue.poll();
        blockingQueue.push("new");
        blockingQueue.poll();
        blockingQueue.poll();
        assertEquals(blockingQueue.size(), 3);
        for (Iterator<String> iterator = blockingQueue.iterator(); iterator.hasNext();){
            String next = iterator.next();
            if(next.equals("new")){
                iterator.remove();
            }
        }
        assertEquals(blockingQueue.size(), 2);
        assertEquals(blockingQueue.poll(), "str3");
        assertEquals(blockingQueue.poll(), "str4");
    }

    @Test(dataProvider = "provideQueues")
    public void testItrRemoveAll(Queue<String> blockingQueue){
        blockingQueue.push("str0");
        blockingQueue.push("str1");
        blockingQueue.push("str2");
        assertEquals(blockingQueue.size(), 3);
        for (Iterator<String> iterator = blockingQueue.iterator(); iterator.hasNext();){
            iterator.next();
            iterator.remove();
        }
        assertEquals(blockingQueue.size(), 0);
    }

}