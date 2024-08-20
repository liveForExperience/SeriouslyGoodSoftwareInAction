package cn.lfe.appendix;

import cn.lfe.Container;

import java.util.Objects;

/**
 * @author chen yue
 * @date 2024-08-20 17:46:31
 */
public class UltimateContainer implements Container {

    private double amount;
    private UltimateContainer parent = this;
    private int size = 1;

    @Override
    public double getAmount() {
        UltimateContainer root = findRootAndCompress();
        return root.amount;
    }

    @Override
    public void addWater(double amount) {
        UltimateContainer root = findRootAndCompress();

        double amountPerContainer = amount / root.size;
        if (root.amount + amountPerContainer < 0) {
            throw new IllegalArgumentException("Not enough water to match the addWater request.");
        }
        root.amount += amount;
    }

    @Override
    public void connectTo(Container rawOther) {
        Objects.requireNonNull(rawOther, "Cannot connect to a null container.");

        if (!(rawOther instanceof UltimateContainer)) {
            throw new UnsupportedOperationException();
        }

        UltimateContainer other = (UltimateContainer) rawOther;
        UltimateContainer root1 = findRootAndCompress(), root2 = other.findRootAndCompress();
        if (root1 == root2) {
            return;
        }

        if (root1.size < root2.size) {
            root1.linkTo(root2);
        } else {
            root2.linkTo(root1);
        }
    }

    private UltimateContainer findRootAndCompress() {
        if (parent != this) {
            parent = parent.findRootAndCompress();
        }

        return parent;
    }

    private void linkTo(UltimateContainer otherRoot) {
        parent = otherRoot;
        otherRoot.amount = combineAmount(otherRoot);
        otherRoot.size += size;
    }

    private double combineAmount(UltimateContainer otherRoot) {
        return (amount * size + otherRoot.amount * otherRoot.size) / (size + otherRoot.size);
    }
}
