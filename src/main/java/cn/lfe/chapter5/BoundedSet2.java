package cn.lfe.chapter5;

import org.checkerframework.checker.units.qual.A;

import java.util.*;

/**
 * @author chen yue
 * @date 2024-08-02 16:32:58
 */
public class BoundedSet2<T> {

    private final LinkedList<T> data;
    private final int capacity;

    public BoundedSet2(int capacity) {
        this.data = new LinkedList<>();
        this.capacity = capacity;
    }

    public BoundedSet2(BoundedSet2<T> other) {
        this.data = new LinkedList<>(other.data);
        this.capacity = other.capacity;
    }

    public void add(T element) {
        BoundedSet2<T> copy = null;
        assert (copy = new BoundedSet2<>(this)) != null;

        if (element == null) {
            throw new NullPointerException();
        }

        data.remove(element);

        if (data.size() == capacity) {
            data.removeFirst();
        }

        data.addLast(element);

        assert postAdd(copy, element): "add filed postcondition!";
        assert checkInvariants(): "add broke an invariant!";
    }

    public boolean contains(T element) {
        return data.contains(element);
    }

    public static <T> List<T> interleaveLists(List<? extends T> a, List<? extends T> b) {
        if (a == null || b == null) {
            throw new NullPointerException("Both lists must be non-null");
        }

        int na = a.size(), nb = b.size();
        if (na != nb) {
            throw new IllegalArgumentException("The lists must have the same length");
        }

        List<T> result = new ArrayList<>();
        for (int i = 0; i < na; i++) {
            result.add(a.get(i));
            result.add(b.get(i));
        }

        assert interleaveCheckPost(a, b, result);
        return result;
    }

    private static <T> boolean interleaveCheckPost(List<? extends T> a, List<? extends T> b, List<T> result) {
        if (result.size() != a.size() + b.size()) {
            return false;
        }

        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) != result.get(i * 2) || b.get(i) != result.get(i * 2 + 1)) {
                return false;
            }
        }

        return true;
    }

    private boolean postAdd(BoundedSet2<T> oldSet, T newElement) {
        if (!data.getLast().equals(newElement)) {
            return false;
        }

        List<T> copyOfCurrent = new ArrayList<>(data);
        copyOfCurrent.remove(newElement);
        oldSet.data.remove(newElement);
        if (oldSet.data.size() == capacity) {
            oldSet.data.removeFirst();
        }

        return oldSet.data.equals(copyOfCurrent);
    }

    private boolean checkInvariants() {
        if (data.size() > capacity) {
            return false;
        }

        Set<T> elements = new HashSet<>();
        for (T element : data) {
            if (!elements.add(element)) {
                return false;
            }
        }

        return true;
    }
}
