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
}
