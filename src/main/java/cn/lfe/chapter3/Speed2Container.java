package cn.lfe.chapter3;

/**
 * @author chen yue
 * @date 2024-07-28 20:30:01
 */
public class Speed2Container {
    private double amount;
    private Speed2Container next = this;

    public void flush() {
        Speed2Container current = this;
        do {
            current.amount = 0;
            current = current.next;
        } while (current != this);
    }

    public int groupSize() {
        int size = 0;
        Speed2Container current = this;
        do {
            size++;
            current = current.next;
        } while (current != this);

        return size;
    }

    public void connectTo(Speed2Container other) {
        Speed2Container oldNext = next;
        next = other.next;
        other.next = oldNext;
    }

    public void addWater(double amount) {
        this.amount += amount;
    }

    public double getAmount() {
        updateGroup();
        return amount;
    }

    private void updateGroup() {
        Speed2Container current = this;
        double totalAmount = 0D;
        int groupSize = 0;

        do {
            totalAmount += current.amount;
            groupSize++;
            current = current.next;
        } while (current != this);
        double newAmount = totalAmount / groupSize;

        do {
            current.amount = newAmount;
            current = current.next;
        } while (current != this);
    }
}
