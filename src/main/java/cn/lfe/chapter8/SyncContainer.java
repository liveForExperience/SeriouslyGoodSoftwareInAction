package cn.lfe.chapter8;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chen yue
 * @date 2024-07-28 20:21:35
 */
public class SyncContainer {

    private static final ReentrantLock GLOBAL_LOCK = new ReentrantLock();

    private Group group = new Group(this);

    public double getAmount() {
        synchronized (group) {
            return group.amountPerContainer;
        }
    }

    public void addWater(double amount) {
        while (true) {
            Object monitor = group;
            synchronized (monitor) {
                if (monitor == group) {
                    double amountPerContainer = amount / group.members.size();
                    group.amountPerContainer += amountPerContainer;
                    return;
                }
            }
        }
    }

    public void connectTo(SyncContainer other) {
        while (true) {
            Object firstMonitor, secondMonitor;
            if (group.id < other.group.id) {
                firstMonitor = group;
                secondMonitor = other.group;
            } else {
                firstMonitor = other.group;
                secondMonitor = group;
            }

            synchronized (firstMonitor) {
                synchronized (secondMonitor) {
                    if (group == other.group) {
                        return;
                    }

                    if ((firstMonitor == group && secondMonitor == other.group) ||
                        (secondMonitor == group && firstMonitor == other.group)) {
                        double newAmount = (group.amountPerContainer + other.group.amountPerContainer) / (group.members.size() + other.group.members.size());

                        group.members.addAll(other.group.members);
                        group.amountPerContainer = newAmount;

                        for (SyncContainer member : group.members) {
                            member.group = group;
                        }
                    }
                }
            }
        }
    }

    private static class Group {
        static final AtomicInteger N_GROUPS = new AtomicInteger();
        double amountPerContainer;
        Set<SyncContainer> members;
        int id = N_GROUPS.incrementAndGet();

        Group(SyncContainer c) {
            members = new HashSet<>();
            members.add(c);
        }
    }
}
