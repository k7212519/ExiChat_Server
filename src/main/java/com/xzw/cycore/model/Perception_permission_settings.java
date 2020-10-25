package com.xzw.cycore.model;

import java.sql.Timestamp;

public class Perception_permission_settings {
    private String user_name;
    private String target_user_name;
    private int perception_level;
    private Timestamp modify_time;

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

    public int getPerception_level() {
        return perception_level;
    }

    public void setPerception_level(int perception_level) {
        this.perception_level = perception_level;
    }

    public Timestamp getModify_time() {
        return modify_time;
    }

    public void setModify_time(Timestamp modify_time) {
        this.modify_time = modify_time;
    }
}
