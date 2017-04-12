package sol.yackeen.skill4skill.models;

/**
 * Created by gmgn on 8/25/2016.
 */
public class Post extends BasePost {


    private int postId;
    private int UserId;
    private String PostDescription;
    private int bids;
    private int timeStamp;
    private String name;

    private String time;

    private String post_content;


    /**
     *
     * @return
     * The postId
     */
    public int getPostId() {
        return postId;
    }

    /**
     *
     * @param postId
     * The PostId
     */
    public void setPostId(int postId) {
        this.postId = postId;
    }

    /**
     *
     * @return
     * The userId
     */
    public int getUserId() {
        return UserId;
    }

    /**
     *
     * @param userId
     * The UserId
     */
    public void setUserId(int userId) {
        this.UserId = userId;
    }

    /**
     *
     * @return
     * The postDescription
     */
    public String getPostDescription() {
        return PostDescription;
    }

    /**
     *
     * @param postDescription
     * The PostDescription
     */
    public void setPostDescription(String postDescription) {
        this.PostDescription = postDescription;
    }

    /**
     *
     * @return
     * The bids
     */
    public int getBids() {
        return bids;
    }

    /**
     *
     * @param bids
     * The Bids
     */
    public void setBids(int bids) {
        this.bids = bids;
    }

    /**
     *
     * @return
     * The timeStamp
     */
    public int getTimeStamp() {
        return timeStamp;
    }

    /**
     *
     * @param timeStamp
     * The TimeStamp
     */
    public void setTimeStamp(int timeStamp) {
        this.timeStamp = timeStamp;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPost_content() {
        return post_content;
    }

    public void setPost_content(String post_content) {
        this.post_content = post_content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public int getPostType() {
        return PostType.vertical;
    }
}
