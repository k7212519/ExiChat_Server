package com.xzw.cycore.mapper;

import com.xzw.cycore.model.Activity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ActivityMapper {
    @Insert("Insert into activity (user_name, activity_type, activity_confidence, create_time) values (#{user_name}, #{activity_type}, #{activity_confidence}, #{create_time})")
    void InsertActivity(Activity activity);
}
