package com.app.news.ShotNews.response;


import com.app.news.ShotNews.entities.Post;

import java.util.List;

public class PostResponse {
    private Post post;
    private List<Post> relatedPosts;

    public PostResponse(Post post, List<Post> relatedPosts) {
        this.post = post;
        this.relatedPosts = relatedPosts;
    }

    // Getters and Setters
    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public List<Post> getRelatedPosts() {
        return relatedPosts;
    }

    public void setRelatedPosts(List<Post> relatedPosts) {
        this.relatedPosts = relatedPosts;
    }
}
