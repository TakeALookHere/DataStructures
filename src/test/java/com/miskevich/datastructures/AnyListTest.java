package com.miskevich.datastructures;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.LinkedList;

import static org.testng.Assert.assertEquals;

public class AnyListTest {

//    private List<String> emptyArrList;
//    private List<String> listArrWithData;
//
//    @BeforeMethod
//    public void initializeTestData(){
//        emptyArrList = new ArrayList<String>();
//        listArrWithData = new ArrayList<String>(){{add("str1"); add("str2"); add("str3");}};
//    }

    @DataProvider(name = "provideLists")
    public Object[][] provideData() {
        java.util.List<String> listArrWithDataD = new java.util.ArrayList<String>(){{add("str1"); add("str2"); add("str3");}};
        java.util.List<String> listLinkWithDataD = new LinkedList<String>(){{add("str1"); add("str2"); add("str3");}};

        return new Object[][] {
                { listArrWithDataD },
                { listLinkWithDataD }
        };
    }


    @Test(dataProvider = "provideLists")
    public void testAddIntoEmptyList(java.util.List<String> arrWithData){
        arrWithData.clear();
        assertEquals(arrWithData.size(), 0);

        arrWithData.add("one");
        assertEquals(arrWithData.get(0), "one");
        assertEquals(arrWithData.size(), 1);
    }

    @Test(dataProvider = "provideLists")
    public void testAddIntoNotEmptyList(java.util.List<String> arrWithData){
        arrWithData.add("str4");
        assertEquals(arrWithData.get(0), "str1");
        assertEquals(arrWithData.get(1), "str2");
        assertEquals(arrWithData.get(2), "str3");
        assertEquals(arrWithData.get(3), "str4");
        assertEquals(arrWithData.size(), 4);
    }

    @Test(dataProvider = "provideLists")
    public void testAddEnsureCapacity(java.util.List<String> arrWithData){
        arrWithData.add("str4");
        arrWithData.add("str5");
        arrWithData.add("str6");
        assertEquals(arrWithData.get(5), "str6");
        assertEquals(arrWithData.size(), 6);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Incorrect index -> 4, " +
            "index should be between 0 and 3", dataProvider = "provideLists")
    public void testAddIndexInvalidIndex(java.util.List<String> arrWithData){
        arrWithData.add(4, "str5");
    }

    @Test(dataProvider = "provideLists")
    public void testAddIndexBegin(java.util.List<String> arrWithData){
        arrWithData.add(0, "begin");
        assertEquals(arrWithData.get(0), "begin");
        assertEquals(arrWithData.get(1), "str1");
        assertEquals(arrWithData.get(2), "str2");
        assertEquals(arrWithData.get(3), "str3");
        assertEquals(arrWithData.size(), 4);
    }

    @Test(dataProvider = "provideLists")
    public void testAddIndexCenter(java.util.List<String> arrWithData){
        arrWithData.add(1, "center");
        assertEquals(arrWithData.get(0), "str1");
        assertEquals(arrWithData.get(1), "center");
        assertEquals(arrWithData.get(2), "str2");
        assertEquals(arrWithData.get(3), "str3");
        assertEquals(arrWithData.size(), 4);
    }

    @Test(dataProvider = "provideLists")
    public void testAddIndexEnd(java.util.List<String> arrWithData){
        arrWithData.add(3, "end");
        assertEquals(arrWithData.get(0), "str1");
        assertEquals(arrWithData.get(1), "str2");
        assertEquals(arrWithData.get(2), "str3");
        assertEquals(arrWithData.get(3), "end");
        assertEquals(arrWithData.size(), 4);
    }

    @Test(dataProvider = "provideLists")
    public void testIndexOfNull(java.util.List<String> arrWithData){
        arrWithData.add(null);
        arrWithData.add("str5");
        int actual = arrWithData.indexOf(null);
        assertEquals(actual, 3);
    }

    @Test(dataProvider = "provideLists")
    public void testIndexOf(java.util.List<String> arrWithData){
        int actual = arrWithData.indexOf("str2");
        assertEquals(actual, 1);
    }

    @Test(dataProvider = "provideLists")
    public void testIndexOfDoesNotExist(java.util.List<String> arrWithData){
        int actual = arrWithData.indexOf("str50");
        assertEquals(actual, -1);
    }

    @Test(dataProvider = "provideLists")
    public void testLastIndexOfNull(java.util.List<String> arrWithData){
        arrWithData.add(null);
        arrWithData.add(null);
        int actual = arrWithData.lastIndexOf(null);
        assertEquals(actual, 4);
    }

    @Test(dataProvider = "provideLists")
    public void testLastIndexOf(java.util.List<String> arrWithData){
        arrWithData.add(2, "str2");
        int actual = arrWithData.lastIndexOf("str2");
        assertEquals(actual, 2);
    }

    @Test(dataProvider = "provideLists")
    public void testLastIndexOfDoesNotExist(java.util.List<String> arrWithData){
        int actual = arrWithData.lastIndexOf("str50");
        assertEquals(actual, -1);
    }

    @Test(dataProvider = "provideLists")
    public void testContainsTrue(java.util.List<String> arrWithData){
        boolean actual = arrWithData.contains("str3");
        assertEquals(actual, true);
    }

    @Test(dataProvider = "provideLists")
    public void testContainsFalse(java.util.List<String> arrWithData){
        boolean actual = arrWithData.contains("str50");
        assertEquals(actual, false);
    }

    @Test(dataProvider = "provideLists")
    public void testClear(java.util.List<String> arrWithData){
        arrWithData.clear();
        assertEquals(arrWithData.size(), 0);
    }

    @Test(dataProvider = "provideLists")
    public void testGetBegin(java.util.List<String> arrWithData){
        String actual = arrWithData.get(0);
        assertEquals(actual, "str1");
    }

    @Test(dataProvider = "provideLists")
    public void testGetCenter(java.util.List<String> arrWithData){
        String actual = arrWithData.get(1);
        assertEquals(actual, "str2");
    }

    @Test(dataProvider = "provideLists")
    public void testGetEnd(java.util.List<String> arrWithData){
        String actual = arrWithData.get(2);
        assertEquals(actual, "str3");
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Incorrect index -> 50, " +
            "index should be between 0 and 3", dataProvider = "provideLists")
    public void testGetInvalidIndex(java.util.List<String> arrWithData){
        arrWithData.get(50);
    }

    @Test(dataProvider = "provideLists")
    public void testRemoveBegin(java.util.List<String> arrWithData){
        arrWithData.remove(0);
        assertEquals(arrWithData.get(0), "str2");
        assertEquals(arrWithData.get(1), "str3");
        assertEquals(arrWithData.size(), 2);
    }

    @Test(dataProvider = "provideLists")
    public void testRemoveCenter(java.util.List<String> arrWithData){
        arrWithData.remove(1);
        assertEquals(arrWithData.get(0), "str1");
        assertEquals(arrWithData.get(1), "str3");
        assertEquals(arrWithData.size(), 2);
    }

    @Test(dataProvider = "provideLists")
    public void testRemoveEnd(java.util.List<String> arrWithData){
        arrWithData.remove(2);
        assertEquals(arrWithData.get(0), "str1");
        assertEquals(arrWithData.get(1), "str2");
        assertEquals(arrWithData.size(), 2);
    }

}