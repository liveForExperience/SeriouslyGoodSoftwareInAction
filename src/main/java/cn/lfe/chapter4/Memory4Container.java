package cn.lfe.chapter4;

/**
 * @author chen yue
 * @date 2024-08-01 14:05:20
 */
public class Memory4Container {
    private static float[] NEXT_OR_AMOUNT = new float[1001];

    public static float getAmount(int containerId) {
        while (NEXT_OR_AMOUNT[containerId] > 0) {
            containerId = (int) NEXT_OR_AMOUNT[containerId] - 1;
        }

        return -NEXT_OR_AMOUNT[containerId];
    }

    public static void connect(int containerId1, int containerId2) {
        int size1 = 0, size2 = 0, lastContainerId1 = containerId1, lastContainerId2 = containerId2;
        while (NEXT_OR_AMOUNT[containerId1] > 0) {
            size1++;
            containerId1 = (int) NEXT_OR_AMOUNT[containerId1] - 1;
        }

        while (NEXT_OR_AMOUNT[containerId2] > 0) {
            size2++;
            containerId2 = (int) NEXT_OR_AMOUNT[containerId2] - 1;
        }

        float container1Amount = getAmount(lastContainerId1);
        NEXT_OR_AMOUNT[lastContainerId1] = containerId2;
        NEXT_OR_AMOUNT[lastContainerId2] += (container1Amount * size1) / (size1 + size2);
    }

    public static void addWater(int containerId, float amount) {
        int size = 0;
        while (NEXT_OR_AMOUNT[containerId] > 0) {
            size++;
            containerId = (int) NEXT_OR_AMOUNT[containerId] - 1;
        }

        NEXT_OR_AMOUNT[containerId] += amount / size;
    }
}
