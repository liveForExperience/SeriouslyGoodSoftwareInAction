package cn.lfe.chapter4;

/**
 * @author chen yue
 * @date 2024-07-30 10:59:29
 */
public class Memory2Container {
    private Memory2Container[] group;
    private float amount;

    public float getAmount() {
        return this.amount;
    }

    public void addWater(float amount) {
        if (this.group == null) {
            this.amount += amount;
            return;
        }

        float newAmount = amount / this.group.length;
        for (Memory2Container container : this.group) {
            container.amount += newAmount;
        }
    }

    public void connectTo(Memory2Container other) {
        if (this.group == null) {
            this.group = new Memory2Container[]{this};
        }

        if (other.group == null) {
            other.group = new Memory2Container[]{other};
        }

        if (this.group == other.group) {
            return;
        }

        int size1 = this.group.length, size2 = other.group.length;
        float amount1 = this.amount, amount2 = other.amount, newAmount = (amount1 * size1 + amount2 * size2) / (size1 + size2);

        int i = 0;
        Memory2Container[] newGroup = new Memory2Container[size1 + size2];
        for (Memory2Container container : this.group) {
            container.group = newGroup;
            container.amount = newAmount;
            newGroup[i++] = container;
        }

        for (Memory2Container container : other.group) {
            container.group = newGroup;
            container.amount = newAmount;
            newGroup[i++] = container;
        }
    }
}
