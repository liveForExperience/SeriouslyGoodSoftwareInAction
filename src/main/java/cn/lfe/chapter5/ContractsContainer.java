package cn.lfe.chapter5;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author chen yue
 * @date 2024-08-02 09:49:06
 */
public class ContractsContainer {

    private List<ContractsContainer> group;
    private double amount;

    public ContractsContainer() {
        this.group = new ArrayList<>();
        this.amount = 0D;
    }

    public double getAmount() {
        return this.amount;
    }

    public void addWater(double amount) {
        double amountPerContainer = amount / group.size();
        if (this.amount + amountPerContainer < 0) {
            throw new IllegalArgumentException("Not enough water to match the addWater request.");
        }

        double oldTotal = groupAmount();
        assert (oldTotal) > 0;

        for (ContractsContainer container : group) {
            container.amount += amountPerContainer;
        }

        assert postAddWater(oldTotal, amount): "addWater failed its postCondition!";
        assert invariantsArePreservedByAddWater(): "addWater broke an invariant!";
    }

    public void connectTo(ContractsContainer other) {
        Objects.requireNonNull(other, "Cannot connect to a null container.");

        if (group == other.group) {
            return;
        }

        int size1 = group.size(), size2 = other.group.size();
        double tot1 = amount * size1, tot2 = other.amount * size2,
                newAmount = (tot1 + tot2) / (size1 + size2);

        group.addAll(other.group);

        for (ContractsContainer referenceContainer : other.group) {
            referenceContainer.group = group;
        }

        for (ContractsContainer referenceContainer : group) {
            referenceContainer.amount = newAmount;
        }

        assert invariantsArePreservedByConnectTo(other): "connectTo broke an invariant!";
    }

    private double groupAmount() {
        double total = 0D;
        for (ContractsContainer container : group) {
            total += container.amount;
        }
        return total;
    }

    private boolean postAddWater(double oldTotal, double addedAmount) {
        return isGroupBalanced() && almostEqual(groupAmount(), oldTotal + addedAmount);
    }

    private boolean isGroupBalanced() {
        for (ContractsContainer container : group) {
            if (container.amount != amount) {
                return false;
            }
        }

        return true;
    }

    private static boolean almostEqual(double x, double y) {
        return Math.abs(x - y) < 1E-4;
    }

    private boolean invariantsArePreservedByAddWater() {
        return isGroupNonNegative() && isGroupBalanced();
    }

    private boolean invariantsArePreservedByConnectTo(ContractsContainer other) {
        return group == other.group &&
                isGroupNonNegative() &&
                isGroupBalanced() &&
                isGroupConsistent();
    }

    private boolean isGroupNonNegative() {
        for (ContractsContainer container : group) {
            if (container.amount < 0) {
                return false;
            }
        }

        return true;
    }

    private boolean isGroupConsistent() {
        for (ContractsContainer container : group) {
            if (container.group != group) {
                return false;
            }
        }

        return true;
    }
}
