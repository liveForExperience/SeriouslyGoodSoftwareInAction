package cn.lfe.chapter8;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2024-08-19 17:07:32
 */
public class Repository<T> {
    private final List<T> elements;
    private final List<Object> monitors;

    public Repository(int size) {
        this.elements = new ArrayList<>(size);
        this.monitors = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            this.elements.add(null);
            this.monitors.add(new Object());
        }
    }

    public T set(int i, T element) {
        synchronized (monitors.get(i)) {
            return elements.set(i, element);
        }
    }

    public void swap(int i, int j) {
        if (i == j) {
            return;
        }

        if (i > j) {
            int tmp = i;
            i = j;
            j = tmp;
        }

        synchronized (monitors.get(i)) {
            synchronized (monitors.get(j)) {
                elements.set(i, elements.set(j, elements.get(i)));
            }
        }
    }
}
