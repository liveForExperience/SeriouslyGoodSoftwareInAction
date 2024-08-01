package cn.lfe.chapter4;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2024-08-01 15:46:16
 */
public class MultiSet1<T> {
    private List<T> data = new ArrayList<>();

    public void add(T element) {
        data.add(element);
    }

    public long count(T element) {
        return data.stream().filter(x -> x.equals(element)).count();
    }
}
