package com.miskevich.datastructures;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class AnyListTest {

    private List<String> emptyArrList;
    private List<String> listArrWithData;

    @BeforeMethod
    public void initializeTestData(){
        emptyArrList = new ArrayList<String>();
        listArrWithData = new ArrayList<String>(){{add("str1"); add("str2"); add("str3");}};
    }


    @Test
    public void testAdd(){
        emptyArrList.add("one");
        assertEquals(emptyArrList.get(0), "one");
        assertEquals(emptyArrList.size(), 1);
        listArrWithData.add("str4");
        assertEquals(listArrWithData.get(0), "str1");
        assertEquals(listArrWithData.get(1), "str2");
        assertEquals(listArrWithData.get(2), "str3");
        assertEquals(listArrWithData.get(3), "str4");
        assertEquals(listArrWithData.size(), 4);
    }

    @Test
    public void testAddEnsureCapacity(){
        listArrWithData.add("str4");
        listArrWithData.add("str5");
        listArrWithData.add("str6");
        assertEquals(listArrWithData.get(5), "str6");
        assertEquals(listArrWithData.size(), 6);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Incorrect index -> 4, " +
            "index should be between 0 and 3")
    public void testAddIndexInvalidIndex(){
        listArrWithData.add(4, "str5");
    }

    @Test
    public void testAddIndexBegin(){
        listArrWithData.add(0, "begin");
        assertEquals(listArrWithData.get(0), "begin");
        assertEquals(listArrWithData.get(1), "str1");
        assertEquals(listArrWithData.get(2), "str2");
        assertEquals(listArrWithData.get(3), "str3");
        assertEquals(listArrWithData.size(), 4);
    }

    @Test
    public void testAddIndexCenter(){
        listArrWithData.add(1, "center");
        assertEquals(listArrWithData.get(0), "str1");
        assertEquals(listArrWithData.get(1), "center");
        assertEquals(listArrWithData.get(2), "str2");
        assertEquals(listArrWithData.get(3), "str3");
        assertEquals(listArrWithData.size(), 4);
    }

    @Test
    public void testAddIndexEnd(){
        listArrWithData.add(3, "end");
        assertEquals(listArrWithData.get(0), "str1");
        assertEquals(listArrWithData.get(1), "str2");
        assertEquals(listArrWithData.get(2), "str3");
        assertEquals(listArrWithData.get(3), "end");
        assertEquals(listArrWithData.size(), 4);
    }

    @Test
    public void testIndexOfNull(){
        listArrWithData.add(null);
        listArrWithData.add("str5");
        int actual = listArrWithData.indexOf(null);
        assertEquals(actual, 3);
    }

    @Test
    public void testIndexOf(){
        int actual = listArrWithData.indexOf("str2");
        assertEquals(actual, 1);
    }

    @Test
    public void testIndexOfDoesNotExist(){
        int actual = listArrWithData.indexOf("str50");
        assertEquals(actual, -1);
    }

    @Test
    public void testLastIndexOfNull(){
        listArrWithData.add(null);
        listArrWithData.add(null);
        int actual = listArrWithData.lastIndexOf(null);
        assertEquals(actual, 4);
    }

    @Test
    public void testLastIndexOf(){
        listArrWithData.add(2, "str2");
        int actual = listArrWithData.lastIndexOf("str2");
        assertEquals(actual, 2);
    }

    @Test
    public void testLastIndexOfDoesNotExist(){
        int actual = listArrWithData.lastIndexOf("str50");
        assertEquals(actual, -1);
    }

    @Test
    public void testContainsTrue(){
        boolean actual = listArrWithData.contains("str3");
        assertEquals(actual, true);
    }

    @Test
    public void testContainsFalse(){
        boolean actual = listArrWithData.contains("str50");
        assertEquals(actual, false);
    }

    @Test
    public void testClear(){
        listArrWithData.clear();
        assertEquals(listArrWithData.size(), 0);
    }

    @Test
    public void testGetBegin(){
        String actual = listArrWithData.get(0);
        assertEquals(actual, "str1");
    }

    @Test
    public void testGetCenter(){
        String actual = listArrWithData.get(1);
        assertEquals(actual, "str2");
    }

    @Test
    public void testGetEnd(){
        String actual = listArrWithData.get(2);
        assertEquals(actual, "str3");
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Incorrect index -> 50, " +
            "index should be between 0 and 3")
    public void testGetInvalidIndex(){
        listArrWithData.get(50);
    }

    @Test
    public void testRemoveBegin(){
        listArrWithData.remove(0);
        assertEquals(listArrWithData.get(0), "str2");
        assertEquals(listArrWithData.get(1), "str3");
        assertEquals(listArrWithData.size(), 2);
    }

    @Test
    public void testRemoveCenter(){
        listArrWithData.remove(1);
        assertEquals(listArrWithData.get(0), "str1");
        assertEquals(listArrWithData.get(1), "str3");
        assertEquals(listArrWithData.size(), 2);
    }

    @Test
    public void testRemoveEnd(){
        listArrWithData.remove(2);
        assertEquals(listArrWithData.get(0), "str1");
        assertEquals(listArrWithData.get(1), "str2");
        assertEquals(listArrWithData.size(), 2);
    }

}