package ArrayList.arrayList.service;

import ArrayList.arrayList.exception.IndexIsOutOfArrayException;
import ArrayList.arrayList.exception.ItemNotFoundException;
import ArrayList.arrayList.exception.NullItemException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class IntegerListImpl implements StringList<Integer> {
    private Integer[] integers;
    private int size;

    public IntegerListImpl() {
        this.integers = new Integer[10];
    }

    public IntegerListImpl(int capacity) {
        this.integers = new Integer[capacity];
    }

    public void itemNotNull(Integer item) {
        if (item == null) {
            throw new NullItemException("Элемент не может быть null");
        }
    }

    public void indexNotMoreThenSizeArray(int index) {
        if (index >= integers.length) {
            throw new IndexIsOutOfArrayException("Индекс больше размера массива");
        }
        if (index < 0) {
            throw new ItemNotFoundException("Элемент не найден");
        }
    }

    @Override
    public Integer add(Integer item) {
        itemNotNull(item);
        integers[size] = item;
        size++;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        itemNotNull(item);
        indexNotMoreThenSizeArray(index);
        for (int i = size; i > index; ) {
            integers[i] = integers[--i];
        }
        integers[index] = item;
        size++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        itemNotNull(item);
        indexNotMoreThenSizeArray(index);
        integers[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        checkByNull(item);
        int index = -1;
        for (int i = 0; i < integers.length; i++) {
            if (integers[i] != null && integers[i].equals(item)) {
                index = i;
                break;
            }
        }
        indexNotMoreThenSizeArray(index);
        while (index < integers.length - 1) {
            integers[index] = integers[++index];
        }
        integers[--size] = null;
        return item;
    }

    @Override
    public Integer remove(int index) {
        indexNotMoreThenSizeArray(index);
        Integer item = integers[index];
        while (index < integers.length - 1) {
            integers[index] = integers[++index];
        }
        integers[--size] = null;
        return item;
    }

    @Override
    public boolean contains(Integer item) {
        sort(0, size - 1);
        int min = 0;
        int max = integers.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (item == integers[mid]) {
                return true;
            }

            if (item < integers[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(Integer item) {
        checkByNull(item);
        for (int i = 0; i < integers.length; i++) {
            if (integers[i] != null && integers[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        checkByNull(item);
        for (int i = integers.length - 1; i >= 0; i--) {
            if (integers[i] != null && integers[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        indexNotMoreThenSizeArray(index);
        return integers[index];
    }

    @Override
    public boolean equals(List otherList) {
        if (this == otherList) return true;
        if (!(otherList instanceof IntegerListImpl that)) return false;
        return size == that.size && Arrays.equals(integers, that.integers);
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
        for (int i = 0; i < integers.length; i++) {
            integers[i] = null;
        }
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(integers, size);
    }

    public void checkByNull(Integer item) {
        if (item == null) {
            throw new NullItemException("Указанный элемент не должен равняться null");
        }
    }

    private Integer[] grow() {
        IntegerListImpl newStrings = new IntegerListImpl(size + size / 2 + 1);
        newStrings.size = size;
        for (int i = 0; i < integers.length; i++) {
            newStrings.integers[i] = integers[i];
        }
        return newStrings.integers;
    }

    private void sort(Integer begin, Integer end) {
        if (begin < end) {
            int partitionIndex = partition(begin, end);

            sort(begin, partitionIndex - 1);
            sort(partitionIndex + 1, end);
        }
    }

    private int partition(int begin, int end) {
        int pivot = integers[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (integers[j] <= pivot) {
                i++;

                swapElements(i, j);
            }
        }

        swapElements(i + 1, end);
        return i + 1;
    }

    private void swapElements(Integer left, Integer right) {
        int temp = integers[left];
        integers[left] = integers[right];
        integers[right] = temp;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(integers);
        return result;
    }
}
