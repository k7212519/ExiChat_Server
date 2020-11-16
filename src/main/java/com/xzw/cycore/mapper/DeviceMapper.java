package com.xzw.cycore.mapper;

import com.xzw.cycore.model.Device;
import com.xzw.cycore.model.TableCode;
import com.xzw.cycore.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface DeviceMapper {
    @Select("Select code from device_table where IMEI = #{IMEI}")
    String getCodeByIMEI(String IMEI);

    @Insert("Insert into device_table (IMEI, create_time) values (#{IMEI}, #{create_time}) ")
    void insertDevice(String IMEI, Timestamp create_time);

    @Select("Select * from device_table WHERE IMEI = #{IMEI}")
    Device findDeviceByIMEI(String IMEI);

    @Select("Select state from device_table WHERE IMEI = #{IMEI}")
    int getActiveState(String IMEI);

    @Select("Select version from version_update WHERE id = '1' ")
    double getVersion();

    @Select("Select url from version_update WHERE id = '1'")
    String getNewVersionUrl();

    @Select("Select * from table_code WHERE code = #{code}")
    TableCode getTableCodeBy(String code);
}
