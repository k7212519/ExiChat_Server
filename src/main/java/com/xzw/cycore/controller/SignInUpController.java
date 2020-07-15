package com.xzw.cycore.controller;

import com.alibaba.fastjson.JSON;
import com.xzw.cycore.mapper.ActivityMapper;
import com.xzw.cycore.mapper.UserMapper;
import com.xzw.cycore.model.Activity;
import com.xzw.cycore.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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



    @GetMapping("/addUser")
    public String addUser(String name, String pwd) {
        User user = new User();
        user.setUser_name(name);
        user.setUser_password(pwd);
        user.setAvatar("../static/img/jsdlkfjsdk.jpg");
        userMapper.Insert(user);
        return "index";
    }

    @ResponseBody
    @GetMapping("/addActivity")
    public String addActivity(String name, int type, int confidence) {
        Activity activity = new Activity();
        activity.setUser_name(name);
        activity.setActivity_type(type);
        activity.setActivity_confidence(confidence);
        activity.setCreate_time(new Timestamp(System.currentTimeMillis()));
        activityMapper.InsertActivity(activity);
        //更新当前用户的实时状态
        userMapper.Update(type, confidence, name);
        return "1";
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
