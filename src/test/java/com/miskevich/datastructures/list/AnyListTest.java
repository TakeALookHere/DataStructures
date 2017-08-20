package com.miskevich.datastructures.list;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;

import static org.testng.Assert.*;

public class AnyListTest {

    @DataProvider(name = "provideLists")
    public Object[][] provideData() {
        List<String> listArrWithData = new ArrayList<String>() {{
            add("str1");
            add("str2");
            add("str3");
        }};
        List<String> listLinkWithData = new LinkedList<String>() {{
            add("str1");
            add("str2");
            add("str3");
        }};

        return new Object[][]{
                {listArrWithData},
                {listLinkWithData}
        };
    }


    @Test(dataProvider = "provideLists")
    public void testAddIntoEmptyList(List<String> arrayWithData) {
        arrayWithData.clear();
        assertEquals(arrayWithData.size(), 0);

        arrayWithData.add("one");
        assertEquals(arrayWithData.get(0), "one");
        assertEquals(arrayWithData.size(), 1);
    }

    @Test(dataProvider = "provideLists")
    public void testAddIntoNotEmptyList(List<String> arrayWithData) {
        arrayWithData.add("str4");
        assertEquals(arrayWithData.get(0), "str1");
        assertEquals(arrayWithData.get(1), "str2");
        assertEquals(arrayWithData.get(2), "str3");
        assertEquals(arrayWithData.get(3), "str4");
        assertEquals(arrayWithData.size(), 4);
    }

    @Test(dataProvider = "provideLists")
    public void testAddEnsureCapacity(List<String> arrayWithData) {
        arrayWithData.add("str4");
        arrayWithData.add("str5");
        arrayWithData.add("str6");
        assertEquals(arrayWithData.get(5), "str6");
        assertEquals(arrayWithData.size(), 6);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Incorrect element index -> 4, " +
            "index should be between 0 and 3 inclusive", dataProvider = "provideLists")
    public void testAddIndexInvalidIndex(List<String> arrayWithData) {
        arrayWithData.add(4, "str5");
    }

    @Test(dataProvider = "provideLists")
    public void testAddIndexEmptyList(List<String> arrayWithData) {
        arrayWithData.clear();
        assertEquals(arrayWithData.size(), 0);
        arrayWithData.add(0, "begin");
        assertEquals(arrayWithData.get(0), "begin");
        assertEquals(arrayWithData.size(), 1);
    }

    @Test(dataProvider = "provideLists")
    public void testAddIndexBegin(List<String> arrayWithData) {
        arrayWithData.add(0, "begin");
        assertEquals(arrayWithData.get(0), "begin");
        assertEquals(arrayWithData.get(1), "str1");
        assertEquals(arrayWithData.get(2), "str2");
        assertEquals(arrayWithData.get(3), "str3");
        assertEquals(arrayWithData.size(), 4);
    }

    @Test(dataProvider = "provideLists")
    public void testAddIndexCenter(List<String> arrayWithData) {
        arrayWithData.add(1, "center");
        assertEquals(arrayWithData.get(0), "str1");
        assertEquals(arrayWithData.get(1), "center");
        assertEquals(arrayWithData.get(2), "str2");
        assertEquals(arrayWithData.get(3), "str3");
        assertEquals(arrayWithData.size(), 4);
    }

    @Test(dataProvider = "provideLists")
    public void testAddIndexEnd(List<String> arrayWithData) {
        arrayWithData.add(3, "end");
        assertEquals(arrayWithData.get(0), "str1");
        assertEquals(arrayWithData.get(1), "str2");
        assertEquals(arrayWithData.get(2), "str3");
        assertEquals(arrayWithData.get(3), "end");
        assertEquals(arrayWithData.size(), 4);
    }

    @Test(dataProvider = "provideLists")
    public void testIndexOfNull(List<String> arrayWithData) {
        arrayWithData.add(null);
        arrayWithData.add("str5");
        int actual = arrayWithData.indexOf(null);
        assertEquals(actual, 3);
    }

    @Test(dataProvider = "provideLists")
    public void testIndexOf(List<String> arrayWithData) {
        int actual = arrayWithData.indexOf("str2");
        assertEquals(actual, 1);
    }

    @Test(dataProvider = "provideLists")
    public void testIndexOfDoesNotExist(List<String> arrayWithData) {
        int actual = arrayWithData.indexOf("str50");
        assertEquals(actual, -1);
    }

    @Test(dataProvider = "provideLists")
    public void testLastIndexOfNull(List<String> arrayWithData) {
        arrayWithData.add(null);
        arrayWithData.add(null);
        int actual = arrayWithData.lastIndexOf(null);
        assertEquals(actual, 4);
    }

    @Test(dataProvider = "provideLists")
    public void testLastIndexOf(List<String> arrayWithData) {
        arrayWithData.add(2, "str2");
        int actual = arrayWithData.lastIndexOf("str2");
        assertEquals(actual, 2);
    }

    @Test(dataProvider = "provideLists")
    public void testLastIndexOfDoesNotExist(List<String> arrayWithData) {
        int actual = arrayWithData.lastIndexOf("str50");
        assertEquals(actual, -1);
    }

    @Test(dataProvider = "provideLists")
    public void testContainsTrue(List<String> arrayWithData) {
        assertTrue(arrayWithData.contains("str3"));
    }

    @Test(dataProvider = "provideLists")
    public void testContainsFalse(List<String> arrayWithData) {
        assertFalse(arrayWithData.contains("str50"));
    }

    @Test(dataProvider = "provideLists", expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp =
            "Incorrect position index -> 3, index should be between 0 and 3 exclusive")
    public void testSetInvalidIndex(List<String> arrayWithData) {
        arrayWithData.set(3, "str4");
    }

    @Test(dataProvider = "provideLists", expectedExceptions = IndexOutOfBoundsException.class, expectedExceptionsMessageRegExp =
            "Current operation is prohibited for empty list: index = 0, size = 0")
    public void testSetEmptyList(List<String> arrayWithData) {
        arrayWithData.clear();
        assertEquals(arrayWithData.size(), 0);
        arrayWithData.set(0, "setValue");
        assertEquals(arrayWithData.get(0), "setValue");
        assertEquals(arrayWithData.size(), 1);
    }

    @Test(dataProvider = "provideLists")
    public void testSetBegin(List<String> arrayWithData) {
        arrayWithData.set(0, "setValue");
        assertEquals(arrayWithData.get(0), "setValue");
        assertEquals(arrayWithData.get(1), "str2");
        assertEquals(arrayWithData.get(2), "str3");
        assertEquals(arrayWithData.size(), 3);
    }

    @Test(dataProvider = "provideLists")
    public void testSetCenter(List<String> arrayWithData) {
        arrayWithData.set(1, "setValue");
        assertEquals(arrayWithData.get(0), "str1");
        assertEquals(arrayWithData.get(1), "setValue");
        assertEquals(arrayWithData.get(2), "str3");
        assertEquals(arrayWithData.size(), 3);
    }

    @Test(dataProvider = "provideLists")
    public void testSetEnd(List<String> arrayWithData) {
        arrayWithData.set(2, "setValue");
        assertEquals(arrayWithData.get(0), "str1");
        assertEquals(arrayWithData.get(1), "str2");
        assertEquals(arrayWithData.get(2), "setValue");
        assertEquals(arrayWithData.size(), 3);
    }

    @Test(dataProvider = "provideLists")
    public void testClear(List<String> arrayWithData) {
        arrayWithData.clear();
        assertEquals(arrayWithData.size(), 0);
    }

    @Test(dataProvider = "provideLists")
    public void testGetBegin(List<String> arrayWithData) {
        String actual = arrayWithData.get(0);
        assertEquals(actual, "str1");
    }

    @Test(dataProvider = "provideLists")
    public void testGetCenter(List<String> arrayWithData) {
        String actual = arrayWithData.get(1);
        assertEquals(actual, "str2");
    }

    @Test(dataProvider = "provideLists")
    public void testGetEnd(List<String> arrayWithData) {
        String actual = arrayWithData.get(2);
        assertEquals(actual, "str3");
    }

    @Test(expectedExceptions = IllegalArgumentException.class, expectedExceptionsMessageRegExp = "Incorrect position index -> 50, " +
            "index should be between 0 and 3 exclusive", dataProvider = "provideLists")
    public void testGetInvalidIndex(List<String> arrayWithData) {
        arrayWithData.get(50);
    }

    @Test(dataProvider = "provideLists", expectedExceptions = IndexOutOfBoundsException.class, expectedExceptionsMessageRegExp =
            "Current operation is prohibited for empty list: index = 0, size = 0")
    public void testRemoveFromEmptyList(List<String> arrayWithData) {
        arrayWithData.clear();
        assertEquals(arrayWithData.size(), 0);
        arrayWithData.remove(0);
    }

    @Test(dataProvider = "provideLists")
    public void testRemoveBegin(List<String> arrayWithData) {
        arrayWithData.remove(0);
        assertEquals(arrayWithData.get(0), "str2");
        assertEquals(arrayWithData.get(1), "str3");
        assertEquals(arrayWithData.size(), 2);
    }

    @Test(dataProvider = "provideLists")
    public void testRemoveCenter(List<String> arrayWithData) {
        arrayWithData.remove(1);
        assertEquals(arrayWithData.get(0), "str1");
        assertEquals(arrayWithData.get(1), "str3");
        assertEquals(arrayWithData.size(), 2);
    }

    @Test(dataProvider = "provideLists")
    public void testRemoveEnd(List<String> arrayWithData) {
        arrayWithData.remove(2);
        assertEquals(arrayWithData.get(0), "str1");
        assertEquals(arrayWithData.get(1), "str2");
        assertEquals(arrayWithData.size(), 2);
    }

    @Test(dataProvider = "provideLists")
    public void testItrHasNextEmptyList(List<String> arrayWithData) {
        arrayWithData.clear();
        assertEquals(arrayWithData.size(), 0);
        Iterator<String> iterator = arrayWithData.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test(dataProvider = "provideLists")
    public void testItrHasNext(List<String> arrayWithData) {
        Iterator<String> iterator = arrayWithData.iterator();
        boolean hasNext;
        for (int i = 0; i < arrayWithData.size(); i++) {
            hasNext = iterator.hasNext();
            assertTrue(hasNext);
            iterator.next();
        }
        hasNext = iterator.hasNext();
        assertFalse(hasNext);
    }

    @Test(dataProvider = "provideLists")
    public void testItrNext(List<String> arrayWithData) {
        Iterator<String> iterator = arrayWithData.iterator();
        for (int i = 0; i < arrayWithData.size(); i++) {
            assertEquals(iterator.next(), arrayWithData.get(i));
        }
    }

    @Test(dataProvider = "provideLists")
    public void testItrRemoveBegin(List<String> arrayWithData) {
        for (Iterator<String> iterator = arrayWithData.iterator(); iterator.hasNext(); ) {
            if (iterator.next().equals("str1")) {
                iterator.remove();
            }
        }
        assertEquals(arrayWithData.size(), 2);
        assertEquals(arrayWithData.get(0), "str2");
        assertEquals(arrayWithData.get(1), "str3");
    }

    @Test(dataProvider = "provideLists")
    public void testItrRemoveCenter(List<String> arrayWithData) {
        for (Iterator<String> iterator = arrayWithData.iterator(); iterator.hasNext(); ) {
            if (iterator.next().equals("str2")) {
                iterator.remove();
            }
        }
        assertEquals(arrayWithData.size(), 2);
        assertEquals(arrayWithData.get(0), "str1");
        assertEquals(arrayWithData.get(1), "str3");
    }

    @Test(dataProvider = "provideLists")
    public void testItrRemoveEnd(List<String> arrayWithData) {
        for (Iterator<String> iterator = arrayWithData.iterator(); iterator.hasNext(); ) {
            if (iterator.next().equals("str3")) {
                iterator.remove();
            }
        }
        assertEquals(arrayWithData.size(), 2);
        assertEquals(arrayWithData.get(0), "str1");
        assertEquals(arrayWithData.get(1), "str2");
    }
}