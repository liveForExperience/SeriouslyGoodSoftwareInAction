package cn.lfe.chapter7;

import cn.lfe.Container;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * A <code>ReadableContainer</code> represents a water container
 * with virtually unlimited capacity
 * <p>
 * Water can be added or removed.
 * Two containers can be connected with a permanent pipe.
 * When two containers are connected, directly or indirectly,
 * the\y become communicating vessels, and water will distribute
 * equally among all of them.
 * </p>
 * The set of all containers connected to this one is called the
 * <i>group</i> of this container.
 *
 * @author chen yue
 * @version 1.0 2024-08-19 15:46:24
 */
public class ReadableContainer implements Container {
    private Set<ReadableContainer> group;
    private double amount;

    /**
     * Create an empty container
     */
    public ReadableContainer() {
        group = new HashSet<>();
        group.add(this);
    }

    /**
     * Returns the amount of water currently held in this container.
     *
     * @return the amount of water currently held in this container
     */
    @Override
    public double getAmount() {
        return this.amount;
    }

    /**
     * Adds water to this container.
     * A negative <code>amount</code> indicates removal of water.
     * In that case, there should be enough water in the group
     * to satisfy this request.
     *
     * @param amount amount the amount of water to be added
     * @throws IllegalArgumentException
 *             if an attempt is made to remove more water than actually present
     */
    @Override
    public void addWater(double amount) {
        double amountPerContainer = amount / group.size();
        for (ReadableContainer c : group) {
            c.amount += amountPerContainer;
        }

        if (this.amount < 0) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Connects this container with another.
     *
     * @param otherContainer other The container that will be connected to this one
     */
    @Override
    public void connectTo(Container otherContainer) {
        if (!(otherContainer instanceof ReadableContainer)) {
            throw new UnsupportedOperationException();
        }

        ReadableContainer other = (ReadableContainer) otherContainer;
        if (isConnectTo(other)) {
            return;
        }

        mergeGroupWith(other.group);
        setAllAmountsTo((groupAmount() + other.groupAmount()) / (groupSize() + other.groupSize()));
    }

    /**
     * Checks whether this container is connected to another one.
     *
     * @param other the container whose connection with this will be checked
     * @return <code>true</code> if this container is connected to <code>other</code>
     */
    public boolean isConnectTo(ReadableContainer other) {
        return this.group == other.group;
    }

    /**
     * Returns the number of containers in the group of this container.
     *
     * @return the size of the group
     */
    public int groupSize() {
        return group.size();
    }

    /**
     * Returns the total amount of water in the group of this container.
     *
     * @return the amount of water in the group
     */
    public double groupAmount() {
        return amount * group.size();
    }

    private void mergeGroupWith(Set<ReadableContainer> otherGroup) {
        this.group.addAll(otherGroup);
        for (ReadableContainer container : otherGroup) {
            container.group = group;
        }
    }

    private void setAllAmountsTo(double amount) {
        for (ReadableContainer container : group) {
            container.amount = amount;
        }
    }
}
