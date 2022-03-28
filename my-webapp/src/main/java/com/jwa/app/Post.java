package src.main.java.com.jwa.app;

import java.util.List;

public class Post {
    protected int postId;
    protected String postBody;
    protected String postTitle;
    protected String postTimestamp;

    public Post() {
    }

    public Post(int id) { 
        this.postId = id;
    }

    public Post(int id, String postBody, String postTitle, String postTimestamp) {
        this(postBody, postTitle, postTimestamp);
        this.postId = id;
    }

    public Post(String postBody, String postTitle, String postTimestamp) {
        this.postBody = postBody;
        this.postTitle = postTitle;
        this.postTimestamp = postTimestamp;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostTimestamp() {
        return postTimestamp;
    }

    public void setPostTimestamp(String postTimestamp) {
        this.postTimestamp = postTimestamp;
    }

    public void add(List<Post> post) {
    }

    public String getCreated() {
        return null;
    }

}
