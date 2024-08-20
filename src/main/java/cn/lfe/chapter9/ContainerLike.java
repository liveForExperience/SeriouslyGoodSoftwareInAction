package cn.lfe.chapter9;

/**
 * @author chen yue
 * @date 2024-08-19 17:18:59
 */
public interface ContainerLike<V, T extends ContainerLike<V, T>> {

    V get();

    void update(V value);

    void connectTo(T other);
}
