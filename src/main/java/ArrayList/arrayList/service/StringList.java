package ArrayList.arrayList.service;

import java.util.List;

public interface StringList<T> {
    T add(T item);

    T add(int index, T item);

    T set(int index, T item);

    T remove(T item);

    T remove(int index);

    boolean contains(T item);

    int indexOf(T item);

    int lastIndexOf(T item);

    T get(int index);

    boolean equals(List<T> otherList);

    // Вернуть фактическое количество элементов.
    int size();

    boolean isEmpty();

    void clear();

    T[] toArray();
}
