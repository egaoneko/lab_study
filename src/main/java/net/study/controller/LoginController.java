package net.study.controller;

import net.study.domain.ForgotPassword;
import net.study.domain.User;
import net.study.domain.enums.ValidEntity;
import net.study.domain.form.ForgotPasswordForm;
import net.study.domain.form.UserCreateForm;
import net.study.domain.validator.ForgotPasswordFormValidator;
import net.study.error.ResourceNotFoundException;
import net.study.repository.ForgotPasswordRepository;
import net.study.repository.UserRepository;
import net.study.service.user.ForgotPasswordService;
import net.study.service.user.UserService;
import net.study.util.MailUtils;
import net.study.util.Utils;
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

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 5/31/15 | 1:51 PM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

@Controller
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ForgotPasswordRepository forgotPasswordRepository;

    @Autowired
    private ForgotPasswordService forgotPasswordService;

    @Autowired
    private Utils utils;

    @Autowired
    private MailUtils mailUtils;

    private final ForgotPasswordFormValidator forgotPasswordFormValidator;

    @Autowired
    public LoginController(ForgotPasswordFormValidator forgotPasswordFormValidator) {
        this.forgotPasswordFormValidator = forgotPasswordFormValidator;
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(forgotPasswordFormValidator);
    }


    @RequestMapping("/login")
    public ModelAndView getLoginPage(@RequestParam Optional<String> error) {
        LOGGER.debug("Getting login page, error={}", error);
        return new ModelAndView("login", "error", error);
    }

    @RequestMapping("/forgot_password/new")
    public String forgotPasswordPage(){
        LOGGER.debug("Forgot password page");
        return "forgotPasswordNew";
    }

    @RequestMapping(value = "/forgot_password/new", method = RequestMethod.POST)
    public ModelAndView handleForgotPassword(@RequestParam(value = "email", required = false) String email){
        LOGGER.debug("Forgot password handler, email={}", email);

        if(email == null){
            return  new ModelAndView("forgotPasswordNew", "error", "Email is empty");
        }

        String error = validateEmail(email);

        if(error != null){
            return new ModelAndView("forgotPasswordNew", "error", error);
        }

        User user = userService.getUserByEmail(email).get();

        ForgotPassword forgotPassword;

        forgotPassword = forgotPasswordService.create(user);

        user.setPasswordHash(utils.getRandomHash(user.getPasswordHash()));
        userRepository.save(user);

        mailUtils.sendForgotPasswordMail(user, forgotPassword.getKeyHash());

        return new ModelAndView("forgotPasswordNew", "message", "Success! Check your email.");
    }

    private String validateEmail(String email){

        try {
            InternetAddress internetAddress = new InternetAddress(email);
            internetAddress.validate();
        } catch (AddressException ex){
            return "Email does not validate";
        }

        try {
            userService.getUserByEmail(email).get();
        } catch (Exception ex){
            return "Email does not exists";
        }

        return null;
    }

    @RequestMapping("/forgot_password/reset/**")
    public ModelAndView resetPassword(@RequestParam(value = "k", required = true) String keyHash) {
        LOGGER.debug("Reset password page, keyHash={}", keyHash);

        ForgotPassword forgotPassword = forgotPasswordRepository.findOneByKeyHash(keyHash);
        validateForgotPassword(forgotPassword);

        return new ModelAndView("resetPassword", "form", new ForgotPasswordForm());
    }

    private void validateForgotPassword(ForgotPassword forgotPassword){
        if(forgotPassword == null) {
            throw new ResourceNotFoundException("Can not find forgot password request");
        }

        if(forgotPassword.getValidEntity() == ValidEntity.INVALID){
            throw new ResourceNotFoundException("This request is invalid");
        }

        long currentTime = System.currentTimeMillis();
        long time = forgotPassword.getExpiredDate().getTime();

        long differentTime = time - currentTime;

        if(differentTime < 0){
            throw new ResourceNotFoundException("Expired Time Over");
        }
        LOGGER.debug("Reset password page, differentTime={}", differentTime);
    }

    @RequestMapping(value = "/forgot_password/reset/**", method = RequestMethod.POST)
    public String handleResetPassword(@Valid @ModelAttribute("form") ForgotPasswordForm form, BindingResult bindingResult,
                                      @RequestParam(value = "k", required = true) String keyHash) {
        LOGGER.debug("Processing reset password form={}, bindingResult={}", form, bindingResult);

        ForgotPassword forgotPassword = forgotPasswordRepository.findOneByKeyHash(keyHash);
        validateForgotPassword(forgotPassword);

        if (bindingResult.hasErrors()) {
            // failed validation
            return "resetPassword";
        }

        forgotPasswordService.resetPassword(form, forgotPassword);

        // ok, redirect
        return "redirect:/";
    }
}