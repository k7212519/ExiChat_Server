package com.xzw.cycore.controller;

import com.xzw.cycore.mapper.DeviceMapper;
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
    public String checkUpdate(double version) {
        double versionNew = deviceMapper.getVersion();
        if (versionNew > version) {
            return deviceMapper.getNewVersionUrl();
        } else {
            return "0";
        }
    }
}
