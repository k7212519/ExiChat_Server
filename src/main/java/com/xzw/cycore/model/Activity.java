package com.xzw.cycore.model;

import java.sql.Timestamp;

public class Activity {
    private String user_name;
    private int activity_type;
    private int activity_confidence;
    private Timestamp create_time;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getActivity_type() {
        return activity_type;
    }

    public void setActivity_type(int activity_type) {
        this.activity_type = activity_type;
    }

    public int getActivity_confidence() {
        return activity_confidence;
    }

    public void setActivity_confidence(int activity_confidence) {
        this.activity_confidence = activity_confidence;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }
}
