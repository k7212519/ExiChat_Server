package com.xzw.cycore.controller;

import com.alibaba.fastjson.JSON;
import com.xzw.cycore.mapper.*;
import com.xzw.cycore.model.Activity;
import com.xzw.cycore.model.HistoryTimeScopeSettings;
import com.xzw.cycore.model.Perception_permission_settings;
import com.xzw.cycore.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;

@Controller
public class SignInUpController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    ActivityMapper activityMapper;

    @Autowired
    NotificationSetMapper notificationSetMapper;

    @Autowired
    TimeScopeSettingMapper timeScopeSettingMapper;

    @Autowired
    PerceptionPermissionMapper perceptionPermissionMapper;



    @ResponseBody
    @GetMapping("/addUser")
    public int addUser(String name, String pwd) {
        if (userMapper.findUserByName(name).size() == 0) {
            User user = new User();
            user.setUser_name(name);
            user.setUser_password(pwd);
            user.setAvatar("../static/img/jsdlkfjsdk.jpg");
            userMapper.Insert(user);
        }

        return 1;
    }

    @ResponseBody
    @GetMapping("/addActivity")
    public int addActivity(String name, int type, int confidence, int level) {
        if (name != null) {
            Activity activity = new Activity();
            activity.setUser_name(name);
            activity.setActivity_type(type);
            activity.setActivity_confidence(confidence);
            activity.setCreate_time(new Timestamp(System.currentTimeMillis()));
            activityMapper.InsertActivity(activity);
            //System.out.println("添加运动信息");
            //更新当前用户的实时状态
            userMapper.Update(type, confidence, name, level);
        }

        //测试
        return 1;
    }

    @ResponseBody
    @GetMapping("/queryByName")
    public String selectUser(String name) {
        return JSON.toJSONString(userMapper.findUserByName(name));
    }

    @ResponseBody
    @GetMapping("/queryAct")
    public String queryActivityNowByName(String name) {
        return JSON.toJSONString(userMapper.SelectActivity(name));
    }

    @ResponseBody
    @GetMapping("/queryActAll")
    public String queryAllActivityNow() {
        return JSON.toJSONString(userMapper.SelectActivityAll());
    }

    @ResponseBody
    @GetMapping("/setLevel")
    public void setActivityPerceptionLevel(int level, String name) {
        userMapper.updatePerceptionLevel(level, name);
    }


    @ResponseBody
    @GetMapping("/uploadNotifySet")
    public void uploadNotificationSetting(String name, String target, int start, int end) {
        if (name != null) {
            notificationSetMapper.InsertNotificationSetting(name, target, start, end, new Timestamp(System.currentTimeMillis()));
        }
    }

    @ResponseBody
    @GetMapping("/uploadTimeSet")
    public void uploadTimeScopeSetting(String name, String type, String start, String end) {
        if (name != null) {
            HistoryTimeScopeSettings historyTimeScopeSettings = new HistoryTimeScopeSettings();
            historyTimeScopeSettings.setUser_name(name);
            historyTimeScopeSettings.setType(type);
            historyTimeScopeSettings.setStart_time(start);
            historyTimeScopeSettings.setEnd_time(end);
            historyTimeScopeSettings.setCreate_time(new Timestamp(System.currentTimeMillis()));
            timeScopeSettingMapper.InsertTimeScopeSetting(historyTimeScopeSettings);
        }
    }

    @ResponseBody
    @GetMapping("/updatePerSet")
    public void updatePerceptionPermissionSetting(String name, String target, int level) {
        if (name != null) {
            Perception_permission_settings perception_permission_settings = perceptionPermissionMapper.QueryPerceptionPermissionByName(name, target);
            if (perception_permission_settings != null) {
                //更新已存在记录
                perceptionPermissionMapper.UpdatePerceptionLevelByName(name, target, level);

            } else {
                //未查到记录，插入新的收据
                perception_permission_settings = new Perception_permission_settings();
                perception_permission_settings.setUser_name(name);
                perception_permission_settings.setTarget_user_name(target);
                perception_permission_settings.setPerception_level(level);
                perception_permission_settings.setModify_time(new Timestamp(System.currentTimeMillis()));
                perceptionPermissionMapper.InsertPerceptionPermission(perception_permission_settings);
            }

            perceptionPermissionMapper.InsertHistoryPerceptionPermissionLevel(name, target, level, new Timestamp(System.currentTimeMillis()));

        }
    }

    @ResponseBody
    @GetMapping("/queryPerSet")
    public int queryPerceptionByName(String name, String target) {
        Perception_permission_settings perception_permission_settings = perceptionPermissionMapper.QueryPerceptionPermissionByName(name, target);
        if (perception_permission_settings != null) {
            return perception_permission_settings.getPerception_level();
        } else {
            //不存在该记录
            return 9;
        }
    }

    /*@ResponseBody
    @GetMapping("/uploadPermissionSet")
    public void uploadPermissionSetting(String name, String target, String level) {

    }*/


    /*@ResponseBody
    @RequestMapping("/uploadAvatar")
    public int uploadAvatar(@RequestParam("avatar") MultipartFile file, String userName) {
        String localPath = System.getProperty("user.dir")+"/src/main/resources/static/upload/avatar";
        if (FileUploadUtil.upload(file, localPath, userName + "_avatar.jpg")) {
            System.out.println("上传成功");
        } else {
            System.out.println("上传失败");
        }
        return 1;
    }*/





    /*
    注册页面
     */
    @GetMapping("/SignUp")
    public String signUp() {
        return "signUp";
    }

    /*
    登录页面
     */
    @GetMapping("/SignIn")
    public String signIn() {
        return "signIn";
    }

   /* @RequestMapping("/doSignIn")
    public String doSignIn() {
        return "redirect:/loginSuccess";
    }*/

    /*
    注册行为
     *//*
    @RequestMapping("/doSignUp")
    public String signUp(@RequestParam("name")String name,
                         @RequestParam("age")int age,
                         @RequestParam("password")String password) {
        *//*User user = new User();
        System.out.println(name+age+password);
        user.setName(name);
        user.setAge(age);
        user.setRoles("ROLE_STUDENT");
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        System.out.println("user信息设置完成");
        userMapper.Insert(user);
        System.out.println("insert success");
        return "signUpResult";*//*
    }*/

    @RequestMapping("/SignInSuccess")
    public String signInSuccess() {
        return "userCenter";
    }
}
