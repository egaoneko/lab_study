package net.study.controller;

import net.study.domain.CurrentUser;
import net.study.domain.form.UserUpdatePasswordForm;
import net.study.domain.validator.UserUpdatePasswordFormValidator;
import net.study.repository.UserRepository;
import net.study.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/1/15 | 11:25 PM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

@Controller
@RequestMapping("/settings")
public class SettingsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SettingsController.class);

    private final UserService userService;
    private final UserUpdatePasswordFormValidator userUpdatePasswordFormValidator;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public SettingsController(UserService userService, UserUpdatePasswordFormValidator userUpdatePasswordFormValidator) {
        this.userService = userService;
        this.userUpdatePasswordFormValidator = userUpdatePasswordFormValidator;
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userUpdatePasswordFormValidator);
    }

    @RequestMapping("/admin")
    public ModelAndView getSettingsAdmin() {
        LOGGER.debug("Getting settings admin form");
        return new ModelAndView("user/settings", "form", new UserUpdatePasswordForm());
    }

    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public String handleSettingsPassword(@Valid @ModelAttribute("form") UserUpdatePasswordForm form, BindingResult bindingResult) {
        LOGGER.debug("Processing user register form={}, bindingResult={}", form, bindingResult);
        if (bindingResult.hasErrors()) {
            // failed validation
            return "user/settings";
        }

        userService.updatePassword(form);

        // ok, redirect
        return "redirect:/settings/admin";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ModelAndView handleSettingsDelete(@ModelAttribute("currentUser")CurrentUser currentUser,
                                             @RequestParam(value = "password", required = true) String password){

        LOGGER.debug("Delete User id={}", currentUser.getId());

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(password, currentUser.getUser().getPasswordHash())) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("user/settings");
            modelAndView.addObject("form", new UserUpdatePasswordForm());
            modelAndView.addObject("deleteError","Passwords do not match");
            return modelAndView;
        }

        userRepository.delete(currentUser.getId());

        SecurityContextHolder.clearContext();

        return new ModelAndView("redirect:/");
    }
}
