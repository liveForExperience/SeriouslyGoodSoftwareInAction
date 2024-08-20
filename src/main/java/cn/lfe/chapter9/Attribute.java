package cn.lfe.chapter9;

import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author chen yue
 * @date 2024-08-19 17:25:30
 */
public interface Attribute<V, S> {

    /**
     * 提供初始的摘要
     */
    S seed();

    /**
     * 用一个值更新一个摘要
     */
    void update(S summary, V value);

    /**
     * 合并2个摘要
     */
    S merge(S summary1, S summary2);

    /**
     * 解包一个摘要
     */
    V report(S summary);

    static <V, S> Attribute<V, S> of(Supplier<S> supplier,
                                     BiConsumer<S, V> updater,
                                     BinaryOperator<S> combiner,
                                     Function<S, V> finisher) {
        return new Attribute<V, S>() {
            @Override
            public S seed() {
                return supplier.get();
            }

            @Override
            public void update(S summary, V value) {
                updater.accept(summary, value);
            }

            @Override
            public S merge(S summary1, S summary2) {
                return combiner.apply(summary1, summary2);
            }

            @Override
            public V report(S summary) {
                return finisher.apply(summary);
            }
        };
    }
}
