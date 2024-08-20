package cn.lfe.chapter9;

import java.util.*;
import java.util.function.BiPredicate;

/**
 * @author chen yue
 * @date 2024-08-19 17:34:11
 */
public class UnionFindNode<V, S> implements ContainerLike<V, UnionFindNode<V, S>> {
    private UnionFindNode<V, S> parent = this;
    private int groupSize = 1;

    private final Attribute<V, S> attribute;
    private S summary;

    public UnionFindNode(Attribute<V, S> dom) {
        this.attribute = dom;
        summary = dom.seed();
    }

    @Override
    public V get() {
        return attribute.report(findRootAndCompress().summary);
    }

    @Override
    public void update(V value) {
        attribute.update(findRootAndCompress().summary, value);
    }

    @Override
    public void connectTo(UnionFindNode<V, S> other) {
        UnionFindNode<V, S> root1 = findRootAndCompress(),
                            root2 = other.findRootAndCompress();

        if (root1 == root2) {
            return;
        }

        int size1 = root1.groupSize, size2 = root2.groupSize;
        S newSummary = attribute.merge(root1.summary, root2.summary);

        if (size1 < size2) {
            root1.parent = root2;
            root2.summary = newSummary;
            root2.groupSize += size1;
        } else {
            root2.parent = root1;
            root1.summary = newSummary;
            root1.groupSize += size2;
        }
    }

    public static <T> Set<Set<T>> partition(Collection<? extends T> collection, BiPredicate<? super T, ? super T> equivalent) {
        Attribute<Set<T>, Set<T>> groupProperty = Attribute.of(
                HashSet::new,
                Set::addAll,
                (set1, set2) -> {
                    Set<T> union = new HashSet<>(set1);
                    union.addAll(set2);
                    return union;
                },
                set -> set
        );

        Map<T, UnionFindNode<Set<T>, Set<T>>> nodeMap = new HashMap<>();
        for (T item : collection) {
            UnionFindNode<Set<T>, Set<T>> node = new UnionFindNode<>(groupProperty);
            node.update(new HashSet<T>(){{this.add(item);}});
            nodeMap.put(item, node);
        }

        for (T item1 : collection) {
            for (T item2 : collection) {
                if (equivalent.test(item1, item2)) {
                    nodeMap.get(item1).connectTo(nodeMap.get(item2));
                }
            }
        }

        Set<Set<T>> result = new HashSet<>();
        for (T item : collection) {
            result.add(nodeMap.get(item).get());
        }
        return result;
    }

    private UnionFindNode<V, S> findRootAndCompress() {
        if (parent != this) {
            parent = parent.findRootAndCompress();
        }

        return parent;
    }
}
