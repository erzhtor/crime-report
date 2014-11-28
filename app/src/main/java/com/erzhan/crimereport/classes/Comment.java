package com.erzhan.crimereport.classes;

/**
 * Created by Erzhan on 22-Nov 14.
 */
public class Comment {
    public String getCommentor_name() {
        return commentor_name;
    }

    public void setCommentor_name(String commentor_name) {
        this.commentor_name = commentor_name;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public int getCrime_id() {
        return crime_id;
    }

    public void setCrime_id(int crime_id) {
        this.crime_id = crime_id;
    }

    private int crime_id;
    private String commentor_name;
    private String commentText;
}
