package ArrayList.arrayList.service;

import ArrayList.arrayList.exception.IndexIsOutOfArrayException;
import ArrayList.arrayList.exception.ItemNotFoundException;
import ArrayList.arrayList.exception.NullItemException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class StringListImpl implements StringList<String> {
    private String[] array;
    private int size;

    public StringListImpl(int i) {
        this.array = new String[10];
    }

    public void itemNotNull(String item) {
        if (item == null) {
            throw new NullItemException("Элемент не может быть null");
        }
    }

    public void indexNotMoreThenSizeArray(int index) {
        if (index >= array.length) {
            throw new IndexIsOutOfArrayException("Индекс больше размера массива");
        }
        if (index < 0) {
            throw new ItemNotFoundException("Элемент не найден");
        }
    }

    @Override
    public String add(String item) {
        itemNotNull(item);
        array[size] = item;
        size++;
        return item;
    }

    @Override
    public String add(int index, String item) {
        indexNotMoreThenSizeArray(index);
        itemNotNull(item);
        for (int i = size; i > index; ) {
            array[i] = array[--i];
        }
        array[index] = item;
        size++;
        return item;
    }

    @Override
    public String set(int index, String item) {
        itemNotNull(item);
        indexNotMoreThenSizeArray(index);
        array[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
        itemNotNull(item);
        int index = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null && array[i].equals(item)) {
                index = i;
                break;
            }
        }
        indexNotMoreThenSizeArray(index);
        while (index < array.length - 1) {
            array[index] = array[++index];
        }
        array[--size] = null;
        return item;
    }

    @Override
    public String remove(int index) {
        indexNotMoreThenSizeArray(index);
        String item = array[index];
        while (index < array.length - 1) {
            array[index] = array[++index];
        }
        array[--size] = null;
        return item;
    }

    @Override
    public boolean contains(String item) {
        itemNotNull(item);
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(String item) {
        itemNotNull(item);
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null && array[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        itemNotNull(item);
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] != null && array[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        indexNotMoreThenSizeArray(index);
        return array[index];
    }

    @Override
    public boolean equals(List<String> otherList) {
        if (this == otherList) return true;
        if (!(otherList instanceof StringListImpl that)) return false;
        return size == that.size && Arrays.equals(array, that.array);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        Arrays.fill(array, null);
        size = 0;
    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(array, size);
    }
}
