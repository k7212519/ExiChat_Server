package com.xzw.cycore.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;

@Mapper
public interface NotificationSetMapper {

    @Insert("Insert into history_activity_notification_settings (user_name, target_user_name, start_activity, end_activity, create_time) " +
            "values (#{user_name}, #{target_user_name}, #{start_activity}, #{end_activity}, #{create_time})")
    void InsertNotificationSetting(String user_name, String target_user_name, int start_activity, int end_activity, Timestamp create_time);
}
