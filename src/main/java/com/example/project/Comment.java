package com.example.project;

import com.google.firebase.database.ServerValue;

public class Comment {
    private String content, username,doctorname;
    float rating;
    private Object timestamp;
    

    public Comment() {
    }


    public Comment(String content, String username, float rating,String doctorname) {
        this.content = content;
        this.username = username;
        this.rating = rating;
        this.timestamp= ServerValue.TIMESTAMP;
        this.doctorname=doctorname;
    }

    public Comment(String content, String username, float rating, Object timestamp,String doctorname) {
        this.content = content;
        this.username = username;
        this.rating = rating;
        this.timestamp = timestamp;
        this.doctorname=doctorname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Object getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Object timestamp) {
        this.timestamp = timestamp;
    }

    public String getDoctorname() {
        return doctorname;
    }
}
