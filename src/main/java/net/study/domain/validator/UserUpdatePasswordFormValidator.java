package net.study.domain.validator;

import net.study.domain.UserUpdatePasswordForm;
import net.study.repository.UserRepository;
import net.study.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/1/15 | 7:31 PM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

@Component
public class UserUpdatePasswordFormValidator implements Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserUpdatePasswordFormValidator.class);
    private final UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public UserUpdatePasswordFormValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(UserUpdatePasswordForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LOGGER.debug("Validating {}", target);
        UserUpdatePasswordForm form = (UserUpdatePasswordForm) target;
        validateOldPasswords(errors, form);
        validatePasswords(errors, form);
    }

    private void validateOldPasswords(Errors errors, UserUpdatePasswordForm form) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(form.getOldPassword(), userRepository.getOne(form.getId()).getPasswordHash())) {
            errors.reject("oldPassword.no_match", "Old Passwords do not match");
        }
    }

    private void validatePasswords(Errors errors, UserUpdatePasswordForm form) {
        if (!form.getPassword().equals(form.getPasswordRepeated())) {
            errors.reject("password.no_match", "Passwords do not match");
        }
    }
}
