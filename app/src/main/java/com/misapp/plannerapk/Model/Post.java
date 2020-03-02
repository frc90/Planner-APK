package com.misapp.plannerapk.Model;

import com.google.gson.annotations.SerializedName;

public class Post {
    private int userId;
    private int Id;
    private String title;

    @SerializedName("body")
    private String text;

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return Id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
