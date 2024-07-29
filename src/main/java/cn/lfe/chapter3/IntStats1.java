package cn.lfe.chapter3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author chen yue
 * @date 2024-07-29 17:31:08
 */
public class IntStats1 {
    private long sum;
    private List<Integer> numbers = new ArrayList<>();

    public void insert(int n) {
        numbers.add(n);
        sum += n;
    }

    public double getAverage() {
        return sum / (double) numbers.size();
    }

    public double getMedian() {
        Collections.sort(numbers);
        int size = numbers.size();
        return size % 2 == 1 ? numbers.get(size / 2) : (numbers.get(size / 2) + numbers.get(size / 2 - 1)) / 2.0;
    }
}
