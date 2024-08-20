package cn.lfe.chapter9;

/**
 * @author chen yue
 * @date 2024-08-19 17:47:13
 */
public class ContainerSummary {
    public static final Attribute<Double, ContainerSummary> OPS = Attribute.of(
            ContainerSummary::new,
            ContainerSummary::update,
            ContainerSummary::merge,
            ContainerSummary::getAmount
    );

    private double amount;
    private final int groupSize;

    public ContainerSummary(double amount, int groupSize) {
        this.amount = amount;
        this.groupSize = groupSize;
    }

    public ContainerSummary() {
        this(0, 1);
    }

    public void update(double increment) {
        this.amount += increment;
    }

    public ContainerSummary merge(ContainerSummary other) {
        return new ContainerSummary(amount + other.amount, groupSize + other.groupSize);
    }

    public double getAmount() {
        return amount / groupSize;
    }
}
