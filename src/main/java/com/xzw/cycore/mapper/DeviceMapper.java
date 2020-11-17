package com.xzw.cycore.mapper;

import com.xzw.cycore.model.Device;
import com.xzw.cycore.model.TableCode;
import com.xzw.cycore.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    //查找校验激活码
    @Select("Select * from table_code WHERE code = #{code}")
    TableCode getTableCodeBy(String code);

    //更新设备激活状态
    @Update("Update device_table SET state=1, purchase_time = #{purchase_time} WHERE IMEI = #{IMEI}")
    int updateDeviceActiveState(String IMEI, Timestamp purchase_time);

    //使当前激活码失效
    @Update("Update table_code SET value=0 WHERE code = #{code}")
    int updateCodeValue(String code);

    @Select("Select purchase_time from device_table WHERE IMEI = #{IMEI}")
    Timestamp queryPurchaseTimeByIMEI(String IMEI);

}
