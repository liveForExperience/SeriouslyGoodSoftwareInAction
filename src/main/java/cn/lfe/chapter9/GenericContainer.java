package cn.lfe.chapter9;

/**
 * @author chen yue
 * @date 2024-08-19 17:52:20
 */
public class GenericContainer extends UnionFindNode<Double, ContainerSummary> {

    public GenericContainer(Attribute<Double, ContainerSummary> dom) {
        super(ContainerSummary.OPS);
    }
}
