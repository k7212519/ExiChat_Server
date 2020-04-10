package com.xzw.cycore.controller;

import com.xzw.cycore.mapper.UserMapper;
import com.xzw.cycore.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignInUpController {

    @Autowired
    UserMapper userMapper;

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
     */
    @RequestMapping("/doSignUp")
    public String signUp(@RequestParam("name")String name,
                         @RequestParam("age")int age,
                         @RequestParam("password")String password) {
        User user = new User();
        System.out.println(name+age+password);
        user.setName(name);
        user.setAge(age);
        user.setRoles("ROLE_STUDENT");
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        System.out.println("user信息设置完成");
        userMapper.Insert(user);
        System.out.println("insert success");
        return "signUpResult";
    }

    @RequestMapping("/SignInSuccess")
    public String signInSuccess() {
        return "userCenter";
    }
}
