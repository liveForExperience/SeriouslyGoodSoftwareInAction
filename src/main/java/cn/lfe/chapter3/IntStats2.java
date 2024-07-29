package cn.lfe.chapter3;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2024-07-29 17:35:07
 */
public class IntStats2 {

    private long sum;
    private List<Integer> numbers = new ArrayList<>();

    public void insert(int n) {
        int i = 0;
        for (Integer number : numbers) {
            if (number > n) {
                break;
            }

            i++;
        }

        numbers.add(i, n);
        sum += n;
    }

    public double getAverage() {
        return sum / (double) numbers.size();
    }

    public double getMedian() {
        int size = numbers.size();
        return size % 2 == 1 ? numbers.get(size / 2) : (numbers.get(size / 2) + numbers.get(size / 2 - 1)) / 2.0;
    }
}