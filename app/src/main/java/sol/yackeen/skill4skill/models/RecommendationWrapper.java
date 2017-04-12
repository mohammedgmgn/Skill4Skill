package sol.yackeen.skill4skill.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gmgn on 9/6/2016.
 */
public class RecommendationWrapper extends BasePost {
    @Override
    public int getPostType() {
        return PostType.horizontal;
    }
    public List<Recommendation> recommendations=new ArrayList<>();
}
