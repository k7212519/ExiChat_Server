package com.xzw.cycore.mapper;

import com.xzw.cycore.model.Device;
import com.xzw.cycore.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeviceMapper {
    @Select("Select code from device_table where IMEI = #{IMEI}")
    String getCodeByIMEI(String IMEI);

    
}
