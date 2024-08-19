package cn.lfe.chapter2;

import cn.lfe.Container;

import java.util.HashSet;
import java.util.Set;

/**
 * &#x4E00;&#x4E2A;&#x6C34;&#x5BB9;&#x5668;
 *
 * @author chen yue
 * @date 2024-07-28 19:55:18
 */
public class ReferenceContainer implements Container {
    private Set<ReferenceContainer> group;
    private double amount;

    public ReferenceContainer() {
        group = new HashSet<>();
        group.add(this);
    }

    @Override
    public double getAmount() {
        return this.amount;
    }

    @Override
    public void addWater(double amount) {
        double amountPerContainer = amount / group.size();
        for (ReferenceContainer c : group) {
            c.amount += amountPerContainer;
        }

        if (this.amount < 0) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void connectTo(Container otherContainer) {
        if (!(otherContainer instanceof ReferenceContainer)) {
            throw new UnsupportedOperationException();
        }

        ReferenceContainer other = (ReferenceContainer) otherContainer;
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
