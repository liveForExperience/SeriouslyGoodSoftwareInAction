package cn.lfe.chapter2;

import java.util.HashSet;
import java.util.Set;

/**
 * 一个水容器
 *
 * @author chen yue
 * @date 2024-07-28 19:55:18
 */
public class ReferenceContainer {
    private Set<ReferenceContainer> group;
    private double amount;

    public ReferenceContainer() {
        group = new HashSet<>();
        group.add(this);
    }

    public double getAmount() {
        return this.amount;
    }

    public void addWater(double amount) {
        double amountPerContainer = amount / group.size();
        for (ReferenceContainer c : group) {
            c.amount += amountPerContainer;
        }
    }

    public void connectTo(ReferenceContainer other) {
        if (group == other.group) {
            return;
        }

        int size1 = group.size(), size2 = other.group.size();
        double tot1 = amount * size1, tot2 = other.amount * size2,
                newAmount = (tot1 + tot2) / (size1 + size2);

        group.addAll(other.group);

        for (ReferenceContainer referenceContainer : other.group) {
            referenceContainer.group = group;
        }

        for (ReferenceContainer referenceContainer : group) {
            referenceContainer.amount = newAmount;
        }
    }
}
