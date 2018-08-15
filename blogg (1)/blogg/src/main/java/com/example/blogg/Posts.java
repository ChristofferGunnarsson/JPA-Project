package com.example.blogg;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Posts {
    private int userID;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postID;
    @NotEmpty
    @NotNull
    private String Blogposts;

    public Posts(){

    }

    public Posts(int userID, String Blogposts) {
        this.userID = userID;
        this.Blogposts = Blogposts;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getBlogposts() {
        return Blogposts;
    }

    public void setBlogposts(String blogposts) {
        this.Blogposts = blogposts;
    }
}