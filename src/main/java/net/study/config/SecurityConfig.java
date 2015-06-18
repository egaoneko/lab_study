package net.study.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 5/31/15 | 1:54 PM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/","/home","/index","/article/list","/article/read/**","/comment/list/**","/user/register").permitAll()
                    .antMatchers("/forgot_password/**","/study/list","/study/read/**").permitAll()
                    .antMatchers("/users/**").hasAuthority("ADMIN")
                    .antMatchers("/public/**","/resources/**","/resources/public/**","/webjars/**").permitAll()
                    .antMatchers("/css/**","/js/**","/img/**","/jui/**","/Material/**","/startbootstrap-creative/**").permitAll()
                    .anyRequest().fullyAuthenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .failureUrl("/login?error")
                    .usernameParameter("email")
                    .permitAll()
                    .and()
                .logout()
                    .logoutUrl("/logout")
                    .deleteCookies("remember-me")
                    .logoutSuccessUrl("/")
                    .permitAll()
                .and()
                .rememberMe();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

}