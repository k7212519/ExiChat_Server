package com.xzw.cycore.service;

import com.xzw.cycore.mapper.UserMapper;
import com.xzw.cycore.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailService<T extends User> implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    /**
     * 登录校验
     * @param name
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        try {
            User user = userMapper.findUserByName(name);
            System.out.println(user.getName());
            if (user == null) {
                throw new UsernameNotFoundException("用户名不存在");
            } else {
                //将user添加到authorities
                List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                System.out.println(user.getRoles());
                if (!StringUtils.isEmpty(user.getRoles())) {
                    String[] roles = user.getRoles().split(",");
                    for (String role : roles) {
                        if (!StringUtils.isEmpty(role)) {
                            authorities.add(new SimpleGrantedAuthority(role.trim()));
                        }
                    }
                }

                return new org.springframework.security.core.userdetails.User(
                        user.getName(),
                        user.getPassword(),
                        authorities
                );

            }
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
