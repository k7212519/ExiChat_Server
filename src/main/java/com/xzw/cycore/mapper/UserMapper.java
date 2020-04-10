package com.xzw.cycore.mapper;

import com.xzw.cycore.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("Insert into user (name, password, age, roles) values ( #{name}, #{password}, #{age}, #{roles} )")
    void Insert(User user);

    @Select("Select * from user where name = #{name}")
    User findUserByName(String name);
}
