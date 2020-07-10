/*
package com.xzw.cycore.config;

import com.xzw.cycore.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailService myUserDetailService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/test", "/SignIn", "/SignUp", "/doSignIn").permitAll()
                .antMatchers(HttpMethod.POST, "/doSignIn", "/doSignUp").permitAll()
                .antMatchers("/loginSuccess").hasAnyRole("ADMIN", "STUDENT")
                .anyRequest().authenticated()
                .and()
                //禁止csrf，防止post被拦截
                .csrf().disable()

                //自定义登录页
                .formLogin()
                .loginPage("/SignIn")
                //自定义登录action
                .loginProcessingUrl("/doSignIn")
                //登录成功跳转页
                .successForwardUrl("/SignInSuccess");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/js/**","/global/**","/assets/**","js/**");
    }
}
*/
