package net.study.domain.validator;

import net.study.domain.form.UserCreateForm;
import net.study.service.user.UserService;
import net.study.util.validator.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 5/31/15 | 1:03 PM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

@Component
public class UserCreateFormValidator implements Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserCreateFormValidator.class);
    private final UserService userService;
    private final EmailValidator emailValidator;

    @Autowired
    public UserCreateFormValidator(UserService userService, EmailValidator emailValidator) {
        this.userService = userService;
        this.emailValidator = emailValidator;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(UserCreateForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LOGGER.debug("Validating {}", target);
        UserCreateForm form = (UserCreateForm) target;
        validateEmailEmpty(errors, form);
        validateNameEmpty(errors, form);
        validatePasswordEmpty(errors, form);
        validatePasswordRepeatedEmpty(errors, form);
        validatePasswordLength(errors, form);
        validatePasswordRepeatedLength(errors, form);
        validatePasswords(errors, form);
        validateEmailAddress(errors, form);
        validateEmail(errors, form);
    }

    private void validateEmailEmpty(Errors errors, UserCreateForm form){
        if (form.getEmail() == null || form.getEmail().equals("")) {
            errors.reject("email.empty", "Email is empty");
        }
    }

    private void validateNameEmpty(Errors errors, UserCreateForm form){
        if (form.getName() == null || form.getName().equals("")) {
            errors.reject("name.empty", "Name is empty");
        }
    }

    private void validatePasswordEmpty(Errors errors, UserCreateForm form){
        if (form.getPassword() == null || form.getPassword().equals("")) {
            errors.reject("password.empty", "Password is empty");
        }
    }

    private void validatePasswordRepeatedEmpty(Errors errors, UserCreateForm form){
        if (form.getPasswordRepeated() == null || form.getPasswordRepeated().equals("")) {
            errors.reject("passwordRepeated.empty", "PasswordRepeated is empty");
        }
    }

    private void validatePasswordLength(Errors errors, UserCreateForm form){
        if (form.getPassword().length() < 7) {
            errors.reject("password.empty", "Password is too short (minimum is 7 characters)");
        }
    }

    private void validatePasswordRepeatedLength(Errors errors, UserCreateForm form){
        if (form.getPasswordRepeated().length() < 7) {
            errors.reject("passwordRepeated.empty", "PasswordRepeated is too short (minimum is 7 characters)");
        }
    }

    private void validatePasswords(Errors errors, UserCreateForm form) {
        if (!form.getPassword().equals(form.getPasswordRepeated())) {
            errors.reject("password.no_match", "Passwords do not match");
        }
    }

    private void validateEmail(Errors errors, UserCreateForm form) {

        try {
            userService.getUserByEmail(form.getEmail()).get();
        } catch (Exception ex){
            return;
        }

        errors.reject("email.exists", "User with this email already exists");
    }

    private void validateEmailAddress(Errors errors, UserCreateForm form) {
        if(!emailValidator.validate(form.getEmail())){
            errors.reject("email.no_validate", "Email does not validate");
        }
    }
}
