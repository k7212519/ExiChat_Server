package com.xzw.cycore.model;

import java.sql.Timestamp;

public class HistoryNotificationSettings {
    private String user_name;
    private String target_user_name;
    private int start_activity;
    private int end_activity;
    private Timestamp create_time;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getTarget_user_name() {
        return target_user_name;
    }

    public void setTarget_user_name(String target_user_name) {
        this.target_user_name = target_user_name;
    }

    public int getStart_activity() {
        return start_activity;
    }

    public void setStart_activity(int start_activity) {
        this.start_activity = start_activity;
    }

    public int getEnd_activity() {
        return end_activity;
    }

    public void setEnd_activity(int end_activity) {
        this.end_activity = end_activity;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }
}
