package net.study.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import net.study.domain.CurrentUser;
import net.study.domain.Study;
import net.study.domain.User;
import net.study.domain.form.UserCreateForm;
import net.study.domain.validator.UserCreateFormValidator;
import net.study.repository.StudyRepository;
import net.study.repository.UserRepository;
import net.study.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 5/30/15 | 6:03 PM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final UserCreateFormValidator userCreateFormValidator;
    private final StudyRepository studyRepository;

    @Autowired
    public UserController(UserService userService, UserCreateFormValidator userCreateFormValidator, StudyRepository studyRepository) {
        this.userService = userService;
        this.userCreateFormValidator = userCreateFormValidator;
        this.studyRepository = studyRepository;
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userCreateFormValidator);
    }

    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @RequestMapping("/{id}")
    public ModelAndView getUserPage(@PathVariable Long id) {
        LOGGER.debug("Getting user page for user={}", id);
        User user = userService.getUserById(id).orElseThrow(() -> new NoSuchElementException(String.format("User=%s not found", id)));
        List < Study > ownStudyList = studyRepository.findAllByUser(user);
        Set<Study> partStudyList = user.getStudySet();

        ModelAndView modelAndView = new ModelAndView("user/user");
        modelAndView.addObject("user",user);
        modelAndView.addObject("ownStudyList",ownStudyList);
        modelAndView.addObject("partStudyList",partStudyList);

        return modelAndView;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping("/create")
    public ModelAndView getUserCreatePage() {
        LOGGER.debug("Getting user create form");
        return new ModelAndView("user/create", "form", new UserCreateForm());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String handleUserCreateForm(@Valid @ModelAttribute("form") UserCreateForm form, BindingResult bindingResult) {
        LOGGER.debug("Processing user create form={}, bindingResult={}", form, bindingResult);
        if (bindingResult.hasErrors()) {
            // failed validation
            return "user/create";
        }
        try {
            userService.create(form);
        } catch (DataIntegrityViolationException e) {
            // probably email already exists - very rare case when multiple admins are adding same user
            // at the same time and form validation has passed for more than one of them.
            LOGGER.warn("Exception occurred when trying to save the user, assuming duplicate email", e);
            bindingResult.reject("email.exists", "Email already exists");
            return "user/create";
        }
        // ok, redirect
        return "redirect:/users";
    }

    @RequestMapping("/register")
    public ModelAndView getUserRegisterPage() {
        LOGGER.debug("Getting user register form");
        return new ModelAndView("user/register", "form", new UserCreateForm());
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String handleUserRegisterForm(@Valid @ModelAttribute("form") UserCreateForm form, BindingResult bindingResult) {
        LOGGER.debug("Processing user register form={}, bindingResult={}", form, bindingResult);
        if (bindingResult.hasErrors()) {
            // failed validation
            return "user/register";
        }
        try {
            userService.create(form);
        } catch (DataIntegrityViolationException e) {
            // probably email already exists - very rare case when multiple admins are adding same user
            // at the same time and form validation has passed for more than one of them.
            LOGGER.warn("Exception occurred when trying to save the user, assuming duplicate email", e);
            bindingResult.reject("email.exists", "Email already exists");
            return "user/register";
        }
        // ok, redirect
        return "redirect:/";
    }
}