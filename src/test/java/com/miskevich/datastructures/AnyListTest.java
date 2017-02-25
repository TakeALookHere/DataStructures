package com.miskevich.datastructures;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class AnyListTest {

    @DataProvider(name = "provideLists")
    public Object[][] provideData() {
        List<String> listArrWithData = new ArrayList<String>(){{add("str1"); add("str2"); add("str3");}};
        List<String> listLinkWithData = new LinkedList<String>(){{add("str1"); add("str2"); add("str3");}};

        return new Object[][] {
                { listArrWithData },
                { listLinkWithData }
        };
    }


    @Test(dataProvider = "provideLists")
    public void testAddIntoEmptyList(List<String> arrWithData){
        arrWithData.clear();
        assertEquals(arrWithData.size(), 0);

        arrWithData.add("one");
        assertEquals(arrWithData.get(0), "one");
        assertEquals(arrWithData.size(), 1);
    }

    @Test(dataProvider = "provideLists")
    public void testAddIntoNotEmptyList(List<String> arrWithData){
        arrWithData.add("str4");
        assertEquals(arrWithData.get(0), "str1");
        assertEquals(arrWithData.get(1), "str2");
        assertEquals(arrWithData.get(2), "str3");
        assertEquals(arrWithData.get(3), "str4");
        assertEquals(arrWithData.size(), 4);
    }

    @Test(dataProvider = "provideLists")
    public void testAddEnsureCapacity(List<String> arrWithData){
        arrWithData.add("str4");
        arrWithData.add("str5");
        arrWithData.add("str6");
        assertEquals(arrWithData.get(5), "str6");
        assertEquals(arrWithData.size(), 6);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Incorrect element index -> 4, " +
            "index should be between 0 and 3", dataProvider = "provideLists")
    public void testAddIndexInvalidIndex(List<String> arrWithData){
        arrWithData.add(4, "str5");
    }

    @Test(dataProvider = "provideLists")
    public void testAddIndexBegin(List<String> arrWithData){
        arrWithData.add(0, "begin");
        assertEquals(arrWithData.get(0), "begin");
        assertEquals(arrWithData.get(1), "str1");
        assertEquals(arrWithData.get(2), "str2");
        assertEquals(arrWithData.get(3), "str3");
        assertEquals(arrWithData.size(), 4);
    }

    @Test(dataProvider = "provideLists")
    public void testAddIndexCenter(List<String> arrWithData){
        arrWithData.add(1, "center");
        assertEquals(arrWithData.get(0), "str1");
        assertEquals(arrWithData.get(1), "center");
        assertEquals(arrWithData.get(2), "str2");
        assertEquals(arrWithData.get(3), "str3");
        assertEquals(arrWithData.size(), 4);
    }

    @Test(dataProvider = "provideLists")
    public void testAddIndexEnd(List<String> arrWithData){
        arrWithData.add(3, "end");
        assertEquals(arrWithData.get(0), "str1");
        assertEquals(arrWithData.get(1), "str2");
        assertEquals(arrWithData.get(2), "str3");
        assertEquals(arrWithData.get(3), "end");
        assertEquals(arrWithData.size(), 4);
    }

    @Test(dataProvider = "provideLists")
    public void testIndexOfNull(List<String> arrWithData){
        arrWithData.add(null);
        arrWithData.add("str5");
        int actual = arrWithData.indexOf(null);
        assertEquals(actual, 3);
    }

    @Test(dataProvider = "provideLists")
    public void testIndexOf(List<String> arrWithData){
        int actual = arrWithData.indexOf("str2");
        assertEquals(actual, 1);
    }

    @Test(dataProvider = "provideLists")
    public void testIndexOfDoesNotExist(List<String> arrWithData){
        int actual = arrWithData.indexOf("str50");
        assertEquals(actual, -1);
    }

    @Test(dataProvider = "provideLists")
    public void testLastIndexOfNull(List<String> arrWithData){
        arrWithData.add(null);
        arrWithData.add(null);
        int actual = arrWithData.lastIndexOf(null);
        assertEquals(actual, 4);
    }

    @Test(dataProvider = "provideLists")
    public void testLastIndexOf(List<String> arrWithData){
        arrWithData.add(2, "str2");
        int actual = arrWithData.lastIndexOf("str2");
        assertEquals(actual, 2);
    }

    @Test(dataProvider = "provideLists")
    public void testLastIndexOfDoesNotExist(List<String> arrWithData){
        int actual = arrWithData.lastIndexOf("str50");
        assertEquals(actual, -1);
    }

    @Test(dataProvider = "provideLists")
    public void testContainsTrue(List<String> arrWithData){
        boolean actual = arrWithData.contains("str3");
        assertEquals(actual, true);
    }

    @Test(dataProvider = "provideLists")
    public void testContainsFalse(List<String> arrWithData){
        boolean actual = arrWithData.contains("str50");
        assertEquals(actual, false);
    }

    @Test(dataProvider = "provideLists", expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp =
            "Incorrect position index -> 3, index should be between 0 and 2")
    public void testSetInvalidIndex(List<String> arrWithData){
        arrWithData.set(3, "str4");
    }

    @Test(dataProvider = "provideLists", expectedExceptions = IndexOutOfBoundsException.class, expectedExceptionsMessageRegExp =
    "Current operation is prohibited for empty list: index = 0, size = 0")
    public void testSetEmptyList(List<String> arrWithData){
        arrWithData.clear();
        assertEquals(arrWithData.size(), 0);
        arrWithData.set(0, "setValue");
        assertEquals(arrWithData.get(0), "setValue");
        assertEquals(arrWithData.size(), 1);
    }

    @Test(dataProvider = "provideLists")
    public void testSetBegin(List<String> arrWithData){
        arrWithData.set(0, "setValue");
        assertEquals(arrWithData.get(0), "setValue");
        assertEquals(arrWithData.get(1), "str2");
        assertEquals(arrWithData.get(2), "str3");
        assertEquals(arrWithData.size(), 3);
    }

    @Test(dataProvider = "provideLists")
    public void testSetCenter(List<String> arrWithData){
        arrWithData.set(1, "setValue");
        assertEquals(arrWithData.get(0), "str1");
        assertEquals(arrWithData.get(1), "setValue");
        assertEquals(arrWithData.get(2), "str3");
        assertEquals(arrWithData.size(), 3);
    }

    @Test(dataProvider = "provideLists")
    public void testSetEnd(List<String> arrWithData){
        arrWithData.set(2, "setValue");
        assertEquals(arrWithData.get(0), "str1");
        assertEquals(arrWithData.get(1), "str2");
        assertEquals(arrWithData.get(2), "setValue");
        assertEquals(arrWithData.size(), 3);
    }

    @Test(dataProvider = "provideLists")
    public void testClear(List<String> arrWithData){
        arrWithData.clear();
        assertEquals(arrWithData.size(), 0);
    }

    @Test(dataProvider = "provideLists")
    public void testGetBegin(List<String> arrWithData){
        String actual = arrWithData.get(0);
        assertEquals(actual, "str1");
    }

    @Test(dataProvider = "provideLists")
    public void testGetCenter(List<String> arrWithData){
        String actual = arrWithData.get(1);
        assertEquals(actual, "str2");
    }

    @Test(dataProvider = "provideLists")
    public void testGetEnd(List<String> arrWithData){
        String actual = arrWithData.get(2);
        assertEquals(actual, "str3");
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Incorrect position index -> 50, " +
            "index should be between 0 and 2", dataProvider = "provideLists")
    public void testGetInvalidIndex(List<String> arrWithData){
        arrWithData.get(50);
    }

    @Test(dataProvider = "provideLists", expectedExceptions = IndexOutOfBoundsException.class, expectedExceptionsMessageRegExp =
            "Current operation is prohibited for empty list: index = 0, size = 0")
    public void testRemoveFromEmptyList(List<String> arrWithData){
        arrWithData.clear();
        assertEquals(arrWithData.size(), 0);
        arrWithData.remove(0);
    }

    @Test(dataProvider = "provideLists")
    public void testRemoveBegin(List<String> arrWithData){
        arrWithData.remove(0);
        assertEquals(arrWithData.get(0), "str2");
        assertEquals(arrWithData.get(1), "str3");
        assertEquals(arrWithData.size(), 2);
    }

    @Test(dataProvider = "provideLists")
    public void testRemoveCenter(List<String> arrWithData){
        arrWithData.remove(1);
        assertEquals(arrWithData.get(0), "str1");
        assertEquals(arrWithData.get(1), "str3");
        assertEquals(arrWithData.size(), 2);
    }

    @Test(dataProvider = "provideLists")
    public void testRemoveEnd(List<String> arrWithData){
        arrWithData.remove(2);
        assertEquals(arrWithData.get(0), "str1");
        assertEquals(arrWithData.get(1), "str2");
        assertEquals(arrWithData.size(), 2);
    }

}