package ArrayList.arrayList.service;

import ArrayList.arrayList.exception.IndexIsOutOfArrayException;
import ArrayList.arrayList.exception.ItemNotFoundException;
import ArrayList.arrayList.exception.NullItemException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static ArrayList.arrayList.service.DataTest.*;
import static org.junit.jupiter.api.Assertions.*;

public class StringListImplTest {
    StringListImpl stringList;

    @BeforeEach
    public void setUp() {
        stringList = new StringListImpl(5);
        stringList.add(ITEM_1);
        stringList.add(ITEM_2);
        stringList.add(ITEM_3);
        stringList.add(ITEM_4);
        stringList.add(ITEM_5);
    }

    @Test
    void shouldReturnEmptyStringList() {
        StringListImpl actual = new StringListImpl(5);
        assertTrue(actual.isEmpty());
    }

    @Test
    void addTest() {
        String actual = stringList.add(ITEM_6);
        assertEquals(ITEM_6, actual);
        assertThrows(NullItemException.class, () -> stringList.add(null));
    }

    @Test
    void addWithIndexTest() {
        String actual = stringList.add(2, ITEM_6);
        assertEquals(ITEM_6, actual);
        assertThrows(NullItemException.class, () -> stringList.add(2, null));
        assertThrows(IndexIsOutOfArrayException.class, () -> stringList.add(10, ITEM_6));
    }

    @Test
    void setTest() {
        String actual = stringList.set(1, ITEM_2);
        assertEquals(ITEM_2, actual);
        assertThrows(NullItemException.class, () -> stringList.add(2, null));
        assertThrows(IndexIsOutOfArrayException.class, () -> stringList.set(10, ITEM_2));
    }

    @Test
    void removeTest() {
        String actual = stringList.remove(ITEM_2);
        assertEquals(ITEM_2, actual);
        assertThrows(NullItemException.class, () -> stringList.remove(null));
        assertThrows(ItemNotFoundException.class, () -> stringList.remove(ITEM_6));
    }

    @Test
    void removeByIndexTest() {
        String actual = stringList.remove(2);
        assertEquals(ITEM_3, actual);
        assertThrows(IndexIsOutOfArrayException.class, () -> stringList.remove(10));
    }

    @Test
    void contains() {
        assertTrue(stringList.contains(ITEM_5));
        assertFalse(stringList.contains(ITEM_6));
    }

    @Test
    void indexOf() {
        stringList.add(ITEM_5);
        assertEquals(4, stringList.indexOf(ITEM_5));
        assertEquals(-1, stringList.indexOf(ITEM_6));
    }

    @Test
    void lastIndexOf() {
        stringList.add(ITEM_5);
        assertEquals(5, stringList.lastIndexOf(ITEM_5));
        assertEquals(-1, stringList.lastIndexOf(ITEM_6));
    }

    @Test
    void get() {
        assertEquals(ITEM_4, stringList.get(3));
        assertThrows(IndexIsOutOfArrayException.class, () -> stringList.get(10));
    }

    @Test
    void testEquals() {
        StringListImpl comparisonList = new StringListImpl(5);
        comparisonList.add(ITEM_1);
        comparisonList.add(ITEM_2);
        comparisonList.add(ITEM_3);
        comparisonList.add(ITEM_4);
        comparisonList.add(ITEM_5);
        assertTrue(stringList.equals(comparisonList));
        comparisonList.remove(1);
        assertFalse(stringList.equals(comparisonList));
    }

    @Test
    void size() {
        int actual = stringList.size();
        assertEquals(5, actual);
    }

    @Test
    void isEmpty() {
        assertFalse(stringList.isEmpty());
        stringList.clear();
        assertTrue(stringList.isEmpty());
    }

    @Test
    void toArray() {
        assertArrayEquals(stringList.toArray(), STRINGS);
    }
}
