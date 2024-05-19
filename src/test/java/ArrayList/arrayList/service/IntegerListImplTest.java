package ArrayList.arrayList.service;

import ArrayList.arrayList.exception.IndexIsOutOfArrayException;
import ArrayList.arrayList.exception.ItemNotFoundException;
import ArrayList.arrayList.exception.NullItemException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IntegerListImplTest {
    IntegerListImpl integerList;

    @BeforeEach
    public void setUp() {
        integerList = new IntegerListImpl(5);
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        integerList.add(4);
        integerList.add(5);
    }

    @Test
    void shouldReturnSortedList() {
        IntegerListImpl expected = new IntegerListImpl(5);
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(4);
        expected.add(5);

        integerList.contains(1);

        assertArrayEquals(expected.toArray(), integerList.toArray());
    }

    @Test
    void shouldReturnEmptyIntegerList() {
        IntegerListImpl actual = new IntegerListImpl();
        assertTrue(actual.isEmpty());
    }

    @Test
    void addTest() {
        Integer actual = integerList.add(6);
        assertEquals(6, actual);
        assertThrows(NullItemException.class, () -> integerList.add(null));
    }

    @Test
    void addWithIndexTest() {
        Integer actual = integerList.add(2, 6);
        assertEquals(6, actual);
        assertThrows(NullItemException.class, () -> integerList.add(2, null));
        assertThrows(IndexIsOutOfArrayException.class, () -> integerList.add(10, 6));
    }

    @Test
    void setTest() {
        Integer actual = integerList.set(1, 2);
        assertEquals(2, actual);
        assertThrows(NullItemException.class, () -> integerList.add(2, null));
        assertThrows(IndexIsOutOfArrayException.class, () -> integerList.set(10, 2));
    }

    @Test
    void removeTest() {
        Integer actual = integerList.remove((Integer) 2);
        assertEquals(2, actual);
        assertThrows(NullItemException.class, () -> integerList.remove(null));
        assertThrows(ItemNotFoundException.class, () -> integerList.remove((Integer) 6));
    }

    @Test
    void removeByIndexTest() {
        Integer actual = integerList.remove(2);
        assertEquals(2, actual);
        assertThrows(IndexIsOutOfArrayException.class, () -> integerList.remove(10));
    }

    @Test
    void contains() {
        assertTrue(integerList.contains(5));
        assertFalse(integerList.contains(6));
    }

    @Test
    void indexOf() {
        integerList.add(5);
        assertEquals(1, integerList.indexOf(5));
        assertEquals(-1, integerList.indexOf(6));
    }

    @Test
    void lastIndexOf() {
        integerList.add(5);
        assertEquals(5, integerList.lastIndexOf(5));
        assertEquals(-1, integerList.lastIndexOf(6));
    }

    @Test
    void get() {
        assertEquals(4, integerList.get(3));
        assertThrows(IndexIsOutOfArrayException.class, () -> integerList.get(10));
    }

    @Test
    void testEquals() {
        IntegerListImpl comparisonList = new IntegerListImpl(5);
        comparisonList.add(1);
        comparisonList.add(5);
        comparisonList.add(2);
        comparisonList.add(4);
        comparisonList.add(3);
        assertTrue(integerList.equals(comparisonList));
        comparisonList.remove(1);
        assertFalse(integerList.equals(comparisonList));
    }

    @Test
    void size() {
        int actual = integerList.size();
        assertEquals(5, actual);
    }

    @Test
    void isEmpty() {
        assertFalse(integerList.isEmpty());
        integerList.clear();
        assertTrue(integerList.isEmpty());
    }

    @Test
    void toArray() {
        assertArrayEquals(integerList.toArray(), new Integer[]{1, 5, 2, 4, 3});
    }
}
