package cn.lfe.chapter9.post;

import cn.lfe.chapter9.Attribute;

/**
 * @author chen yue
 * @date 2024-08-20 17:16:54
 */
public class PostSummary {

    public static final Attribute<Integer, PostSummary> OPS = Attribute.of(
            PostSummary::new,
            PostSummary::update,
            PostSummary::merge,
            PostSummary::getCount
    );

    private int likeCount;

    public PostSummary (int likeCount) {
        this.likeCount = likeCount;
    }

    public PostSummary() {}

    public void update(int likes) {
        likeCount += likes;
    }

    public PostSummary merge(PostSummary summary) {
        return new PostSummary(likeCount + summary.likeCount);
    }

    public int getCount() {
        return likeCount;
    }
}
