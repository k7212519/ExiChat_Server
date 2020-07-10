package com.xzw.cycore.model;

public class User {
    private String user_name;
    private String user_password;
    private String avatar;
    private int activity_type_now;
    private int activity_confidence_now;

    public int getActivity_type_now() {
        return activity_type_now;
    }

    public void setActivity_type_now(int activity_type_now) {
        this.activity_type_now = activity_type_now;
    }

    public int getActivity_confidence_now() {
        return activity_confidence_now;
    }

    public void setActivity_confidence_now(int activity_confidence_now) {
        this.activity_confidence_now = activity_confidence_now;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
