package cn.lfe.chapter3;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chen yue
 * @date 2024-07-28 20:21:35
 */
public class Speed1Container {

    private Group group = new Group(this);

    public void flush() {
        group.amountPerContainer = 0;
    }

    public int groupSize() {
        return group.members.size();
    }

    public double getAmount() {
        return group.amountPerContainer;
    }

    public void addWater(double amount) {
        double amountPerContainer = amount / group.members.size();
        group.amountPerContainer += amountPerContainer;
    }

    public void connectTo(Speed1Container other) {
        if (group == other.group) {
            return;
        }

        double newAmount = (group.amountPerContainer + other.group.amountPerContainer) / (group.members.size() + other.group.members.size());

        group.members.addAll(other.group.members);
        group.amountPerContainer = newAmount;

        for (Speed1Container member : group.members) {
            member.group = group;
        }
    }

    private static class Group {
        double amountPerContainer;
        Set<Speed1Container> members;

        Group(Speed1Container c) {
            members = new HashSet<>();
            members.add(c);
        }
    }
}
