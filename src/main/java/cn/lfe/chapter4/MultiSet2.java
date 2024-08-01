package cn.lfe.chapter4;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2024-08-01 15:49:52
 */
public class MultiSet2<T> {
    private List<T> elements = new ArrayList<>();
    private List<Long> repetitions = new ArrayList<>();

    public void add(T element) {
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i) == element) {
                repetitions.set(i, repetitions.get(i) + 1);
                return;
            }
        }

        elements.add(element);
        repetitions.add(1L);
    }

    public long count(T element) {
        for (int i = 0; i < elements.size(); i++) {
            if (element == elements.get(i)) {
                return repetitions.get(i);
            }
        }

        return 0L;
    }
}
