package cn.lfe.chapter4;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2024-07-30 10:31:42
 */
public class Memory1Container {
    private List<Memory1Container> group;
    private float amount;

    public float getAmount() {
        return this.amount;
    }

    public void addWater(float amount) {
        if (this.group == null) {
            this.amount += amount;
            return;
        }

        float newAmount = amount / this.group.size();
        for (Memory1Container container : this.group) {
            container.amount += newAmount;
        }
    }

    public void connectTo(Memory1Container other) {
        if (this.group == null) {
            this.group = new ArrayList<>();
            this.group.add(this);
        }

        if (other.group == null) {
            other.group = new ArrayList<>();
            other.group.add(this);
        }

        if (this.group == other.group) {
            return;
        }

        int size1 = this.group.size(), size2 = other.group.size();
        float amount1 = this.amount, amount2 = other.amount, newAmount = (amount1 * size1 + amount2 * size2) / (size1 + size2);

        this.group.addAll(other.group);

        for (Memory1Container container : other.group) {
            container.group = group;
        }

        for (Memory1Container container : this.group) {
            container.amount = newAmount;
        }
    }
}
