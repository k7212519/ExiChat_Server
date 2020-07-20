package com.xzw.cycore.controller;

import com.alibaba.fastjson.JSON;
import com.xzw.cycore.mapper.ActivityMapper;
import com.xzw.cycore.mapper.UserMapper;
import com.xzw.cycore.model.Activity;
import com.xzw.cycore.model.User;
import com.xzw.cycore.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.rmi.runtime.Log;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class SignInUpController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    ActivityMapper activityMapper;



    @ResponseBody
    @GetMapping("/addUser")
    public int addUser(String name, String pwd) {
        if (userMapper.findUserByName(name) == null) {
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
