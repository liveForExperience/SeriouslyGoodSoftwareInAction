package cn.lfe.chapter5;

import java.util.LinkedList;

/**
 * @author chen yue
 * @date 2024-08-02 16:32:58
 */
public class BoundedSet1<T> {

    private final LinkedList<T> data;
    private final int capacity;

    public BoundedSet1(int capacity) {
        this.data = new LinkedList<>();
        this.capacity = capacity;
    }

    public void add(T element) {
        if (element == null) {
            throw new NullPointerException();
        }

        data.remove(element);

        if (data.size() == capacity) {
            data.removeFirst();
        }

        data.addLast(element);
    }

    public boolean contains(T element) {
        return data.contains(element);
    }
}
