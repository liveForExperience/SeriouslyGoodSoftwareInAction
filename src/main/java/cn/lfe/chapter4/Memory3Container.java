package cn.lfe.chapter4;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2024-07-30 16:44:48
 */
public class Memory3Container {
    private static int[] group = new int[0];
    private static float[] amount = new float[0];

    public static float getAmount(int containerId) {
        int groupId = group[containerId];
        return amount[groupId];
    }

    public static int newContainer() {
        int nContainers = group.length, nGroup = group.length;
        amount = Arrays.copyOf(amount, nGroup + 1);
        group = Arrays.copyOf(group, nContainers + 1);

        group[nContainers] = nGroup;
        return nContainers;
    }

    public static void connect(int containerId1, int containerId2) {
        int groupId1 = group[containerId1], groupId2 = group[containerId2];
        if (groupId1 == groupId2) {
            return;
        }

        int size1 = groupSize(groupId1), size2 = groupSize(groupId2);
        float amount1 = amount[groupId1], amount2 = amount[groupId2];

        amount[groupId1] = (amount1 * size1 + amount2 * size2) / (size1 + size2);

        for (int i = 0; i < group.length; i++) {
            if (group[i] == groupId2) {
                group[i] = groupId1;
            }
        }

        removeGroupAndDefrag(groupId2);
    }

    public void addWater(int containerId, float addAmount) {
        int size = groupSize(containerId);
        amount[containerId] += addAmount / size;
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

    private static void removeGroupAndDefrag(int groupId) {
        for (int i = 0; i < group.length; i++) {
            if (group[i] == group.length - 1) {
                group[i] = groupId;
            }
        }
    }
}
