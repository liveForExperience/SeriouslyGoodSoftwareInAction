package cn.lfe.chapter9.post;

import cn.lfe.chapter9.Attribute;
import cn.lfe.chapter9.UnionFindNode;

/**
 * @author chen yue
 * @date 2024-08-20 17:21:01
 */
public class Post extends UnionFindNode<Integer, PostSummary> {
    public Post(Attribute<Integer, PostSummary> dom) {
        super(PostSummary.OPS);
    }
}
