package com.xzw.cycore.mapper;

import com.xzw.cycore.model.Activity;
import com.xzw.cycore.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {
    @Insert("Insert into user (user_name, user_password, avatar) values (#{user_name}, #{user_password}, #{avatar})")
    void Insert(User user);

    @Select("Select * from user where user_name = #{user_name}")
    List<User> findUserByName(String user_name);

    @Update("Update user SET activity_type_now = #{activity_type_now}, activity_confidence_now = #{activity_confidence_now}, activity_perception_level = #{activity_perception_level} WHERE user_name=#{user_name}")
    void Update(int activity_type_now, int activity_confidence_now, String user_name, int activity_perception_level);

    @Select("Select activity_type_now, activity_confidence_now, activity_perception_level FROM user WHERE user_name = #{user_name}")
    List<User> SelectActivity(String user_name);

    @Select("Select user_name, activity_type_now, activity_confidence_now , activity_perception_level FROM user")
    List<User> SelectActivityAll();

    @Update("Update user SET activity_perception_level = #{activity_perception_level} WHERE user_name = #{user_name}")
    void updatePerceptionLevel(int activity_perception_level, String user_name);
}
