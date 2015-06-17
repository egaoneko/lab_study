package net.study.controller;

import net.study.domain.Assets;
import net.study.domain.CurrentUser;
import net.study.domain.User;
import net.study.domain.form.UserUpdatePasswordForm;
import net.study.domain.validator.UserUpdatePasswordFormValidator;
import net.study.repository.UserRepository;
import net.study.service.user.UserService;
import net.study.util.ImageValidator;
import net.study.util.Utils;
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
import org.springframework.web.multipart.MultipartFile;
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
    private Utils utils;

    @Autowired
    private ImageValidator imageValidator;

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

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public ModelAndView updateSettingsAdmin(@ModelAttribute("currentUser")CurrentUser currentUser,
                                            @RequestParam(value = "file",required = false) MultipartFile file,
                                            @RequestParam(value = "name", required = false) String name) {
        LOGGER.debug("Getting settings admin update, name={}", name);

        ModelAndView modelAndView = new ModelAndView("user/settings", "form", new UserUpdatePasswordForm());

        User user = currentUser.getUser();

        if(name != null && !name.equals("")){
            user.setName(name);
            userRepository.save(user);
            modelAndView.addObject("nameSuccess", "The name has been changed successfully");
        } else {
            modelAndView.addObject("nameError", "Name is empty");
        }

        if(file != null && imageValidator.validate(file.getOriginalFilename())){
            LOGGER.debug("File name={}, validated={}", file.getOriginalFilename(), imageValidator.validate(file.getOriginalFilename()));
            Assets assets = utils.profileSaveHelper(file, user);

            if(assets != null){
                modelAndView.addObject("avatarSuccess", "The avatar has been changed successfully");
            }
        }

        return modelAndView;
    }

    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public String handleSettingsPassword(@Valid @ModelAttribute("form") UserUpdatePasswordForm form, BindingResult bindingResult) {
        LOGGER.debug("Processing user register form={}, bindingResult={}", form, bindingResult);
        if (bindingResult.hasErrors()) {
            // failed validation
            return "user/settings";
        }

        userService.updatePassword(form);

        SecurityContextHolder.clearContext();

        // ok, redirect
        return "redirect:/";
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
