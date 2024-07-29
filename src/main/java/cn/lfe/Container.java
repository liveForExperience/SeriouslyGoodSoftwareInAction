package cn.lfe;

/**
 * @author chen yue
 * @date 2024-07-28 19:57:20
 */
public interface Container {

    /**
     * 获取水量
     *
     * @return 水量
     */
    double getAmount();

    /**
     * 增加水
     *
     * @param amount 水量
     */
    void addWater(double amount);

    /**
     * 连接容器
     *
     * @param other 其他容器
     */
    void connectTo(Container other);
}
