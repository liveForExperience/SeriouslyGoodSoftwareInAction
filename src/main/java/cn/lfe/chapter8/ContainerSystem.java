package cn.lfe.chapter8;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2024-08-19 16:53:34
 */
public class ContainerSystem {
    private static int[] group = new int[0];
    private static double[] amount = new double[0];

    public ContainerSystem(int containerCount) {
        this.group = new int[containerCount];
        this.amount = new double[containerCount];
        for (int i = 0; i < containerCount; i++) {
            group[i] = i;
        }
    }

    public ContainerSystem(ContainerSystem old, int length) {
        this.group = Arrays.copyOf(old.group, length);
        this.amount = Arrays.copyOf(old.amount, length);
    }

    public double getAmount(int containerId) {
        return amount[group[containerId]];
    }

    public ContainerSystem addContainer() {
        final int containerCount = this.group.length;
        ContainerSystem result = new ContainerSystem(this, containerCount + 1);
        result.group[containerCount] = containerCount;
        return result;
    }

    public ContainerSystem addWater(int containerId, double amount) {
        if (amount == 0) {
            return this;
        }

        ContainerSystem result = new ContainerSystem(this, group.length);
        int groupId = group[containerId], groupSize = groupSize(groupId);
        result.amount[groupId] += amount / groupSize;
        return result;
    }

    private static int groupSize(int groupId) {
        int size = 0;
        for (int otherGroupId : group) {
            if (otherGroupId == groupId) {
                size++;
            }
        }
        return size;
    }
}
