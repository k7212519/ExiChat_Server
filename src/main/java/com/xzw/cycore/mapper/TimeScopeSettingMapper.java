package com.xzw.cycore.mapper;

import com.xzw.cycore.model.HistoryTimeScopeSettings;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;

@Mapper
public interface TimeScopeSettingMapper {
    @Insert("Insert into history_activity_time_scope_settings (user_name, type, start_time, end_time, create_time) " +
            "values (#{user_name}, #{type}, #{start_time}, #{end_time}, #{create_time})")
    void InsertTimeScopeSetting(HistoryTimeScopeSettings historyTimeScopeSettings);
}
