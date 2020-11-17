package com.xzw.cycore.controller;

import com.xzw.cycore.mapper.DeviceMapper;
import com.xzw.cycore.model.Device;
import com.xzw.cycore.model.TableCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
public class VtokenAssistController {

    @Autowired
    DeviceMapper deviceMapper;

    @GetMapping("/queryCode")
    public String getCode(String IMEI) {
        String code = deviceMapper.getCodeByIMEI(IMEI);
        return code;
    }

    @GetMapping("/insertDevice")
    public boolean inset(String IMEI) {
        if (deviceMapper.findDeviceByIMEI(IMEI) == null) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            deviceMapper.insertDevice(IMEI, timestamp);
            return true;
        }
        return false;
    }

    @GetMapping("/queryActiveState")
    public int queryActiveState(String IMEI) {
        if (deviceMapper.findDeviceByIMEI(IMEI) == null) {
            inset(IMEI);
            return 0;
        } else {
            return deviceMapper.getActiveState(IMEI);
        }
    }

    @GetMapping("/checkUpdate")
    public String checkUpdate(String version) {
        double versionNew = deviceMapper.getVersion();
        if (versionNew > Double.parseDouble(version)) {
            return deviceMapper.getNewVersionUrl();
        } else {
            return "0";
        }
    }

    //return 0激活码错误  1success  2激活码失效
    @GetMapping("/activeByCode")
    public int activeByCode(String code, String IMEI) {
        if (!code.isEmpty()) {
            TableCode tableCode = deviceMapper.getTableCodeBy(code);
            if (tableCode != null) {
                if (tableCode.getValue() == 1) {
                    //激活成功
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    if (deviceMapper.updateDeviceActiveState(IMEI, timestamp) == 1) {
                        //使当前激活码失效
                        deviceMapper.updateCodeValue(code);
                        return 1;
                    } else {
                        return 0;
                    }
                } else if (tableCode.getValue() == 0) {
                    //激活码已经失效
                    return 2;
                } else {
                    return 0;
                }
            }
            //激活码错误
            return 0;
        }
        return 0;
    }

    @GetMapping("/queryPurchaseTime")
    public String queryPurchaseTime(String IMEI) {
        Timestamp purchaseTime = deviceMapper.queryPurchaseTimeByIMEI(IMEI);
        if (purchaseTime != null) {
            return purchaseTime.toString();
        } else {
            return "0";
        }
    }
}
