package net.study.service.user;

import net.study.domain.User;
import net.study.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/14/15 | 3:14 AM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

@Service
public class LoginSuccessHandler implements ApplicationListener<AuthenticationSuccessEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginSuccessHandler.class);
    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public LoginSuccessHandler(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        String userName = ((UserDetails) event.getAuthentication().getPrincipal()).getUsername();
        User user = this.userService.getUserByEmail(userName).orElseThrow(() -> new NoSuchElementException(String.format("User=%s not found", userName)));
        user.setLastDate(new Date());
        LOGGER.debug("Login user email={}, login time={}", userName, user.getLastDate());

        userRepository.save(user);
    }
}
