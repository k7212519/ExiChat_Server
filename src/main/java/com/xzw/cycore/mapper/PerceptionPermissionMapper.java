package com.xzw.cycore.mapper;

import com.xzw.cycore.model.Perception_permission_settings;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface PerceptionPermissionMapper {
    @Insert("Insert into perception_permission_settings (user_name, target_user_name, perception_level, modify_time)" +
            "values (#{user_name}, #{target_user_name}, #{perception_level}, #{modify_time})")
    void InsertPerceptionPermission(Perception_permission_settings perception_permission_settings);

    @Select("Select * from perception_permission_settings where user_name = #{user_name} AND target_user_name = #{target_user_name}")
    Perception_permission_settings QueryPerceptionPermissionByName(String user_name, String target_user_name);

    @Update("Update perception_permission_settings SET perception_level = #{perception_level} " +
            "WHERE user_name= #{user_name} AND target_user_name= #{target_user_name}")
    void UpdatePerceptionLevelByName(String user_name, String target_user_name, int perception_level);
}
