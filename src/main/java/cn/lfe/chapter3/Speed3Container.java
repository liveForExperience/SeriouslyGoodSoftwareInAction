package cn.lfe.chapter3;

/**
 * @author chen yue
 * @date 2024-07-28 20:53:13
 */
public class Speed3Container {
    private double amount;
    private Speed3Container parent = this;
    private int size = 1;

    public void flush() {
        findRootAndCompress().amount = 0;
    }

    public int groupSize() {
        return findRootAndCompress().size;
    }

    public double getAmount() {
        Speed3Container root = findRootAndCompress();
        return root.amount;
    }

    public void addWater(double amount) {
        Speed3Container root = findRootAndCompress();
        root.amount += amount;
    }

    public void connectTo(Speed3Container other) {
        Speed3Container root1 = findRootAndCompress(), root2 = other.findRootAndCompress();
        if (root1 == root2) {
            return;
        }

        int size1 = root1.size, size2 = root2.size;
        double amount1 = root1.amount, amount2 = root2.amount, newAmount = (amount1 * size1 + amount2 * size2) / (size1 + size2);

        if (size1 <= size2) {
            root1.parent = root2;
            root2.amount = newAmount;
            root2.size += size1;
        } else {
            root2.parent = root1;
            root1.amount = newAmount;
            root1.size += size2;
        }
    }

    private Speed3Container findRootAndCompress() {
        if (parent != this) {
            parent = parent.findRootAndCompress();
        }

        return parent;
    }
}
