package com.xzw.cycore.mapper;

import com.xzw.cycore.model.Activity;
import com.xzw.cycore.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("Insert into user (user_name, user_password, avatar) values (#{user_name}, #{user_password}, #{avatar})")
    void Insert(User user);

    /*@Select("Select * from user where name = #{name}")
    User findUserByName(String name);*/
}
