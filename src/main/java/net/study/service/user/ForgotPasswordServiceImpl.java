package net.study.service.user;

import com.mysema.query.BooleanBuilder;
import net.study.domain.ForgotPassword;
import net.study.domain.QForgotPassword;
import net.study.domain.User;
import net.study.domain.enums.ValidEntity;
import net.study.domain.form.ForgotPasswordForm;
import net.study.repository.ForgotPasswordRepository;
import net.study.repository.UserRepository;
import net.study.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/14/15 | 10:18 PM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ForgotPasswordServiceImpl.class);
    private final ForgotPasswordRepository forgotPasswordRepository;
    private final UserRepository userRepository;
    private final Utils utils;

    @Autowired
    public ForgotPasswordServiceImpl(ForgotPasswordRepository forgotPasswordRepository, UserRepository userRepository, Utils utils) {
        this.forgotPasswordRepository = forgotPasswordRepository;
        this.userRepository = userRepository;
        this.utils = utils;
    }

    @Override
    public Iterable<ForgotPassword> findAllByUserANDBYValidEntity(User user, ValidEntity validEntity) {
        LOGGER.debug("Find forgot password By user={}, validEntity={}", user, validEntity);

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(QForgotPassword.forgotPassword.user.id.eq(user.getId()));
        builder.and(QForgotPassword.forgotPassword.validEntity.eq(validEntity));

        return forgotPasswordRepository.findAll(builder);
    }

    @Override
    public ForgotPassword create(User user) {

        ForgotPassword forgotPassword = new ForgotPassword();
        forgotPassword.setUser(user);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, 15);
        forgotPassword.setExpiredDate(calendar.getTime());
        forgotPassword.setValidEntity(ValidEntity.VALID);

        forgotPassword.setKeyHash(utils.getRandomHash(calendar.getTime().toString()));

        LOGGER.debug("Forgot Password Generate user email={}, random={}, expiredDate={}", user.getEmail(), forgotPassword.getExpiredDate());
        LOGGER.debug("Forgot Password Generate key={}", forgotPassword.getKeyHash());

        Iterable<ForgotPassword> forgotPasswordList = findAllByUserANDBYValidEntity(forgotPassword.getUser(), ValidEntity.VALID);
        for(ForgotPassword list : forgotPasswordList){
            list.setValidEntity(ValidEntity.INVALID);
            forgotPasswordRepository.save(list);
        }

        return forgotPasswordRepository.save(forgotPassword);
    }

    @Override
    public User resetPassword(ForgotPasswordForm form, ForgotPassword forgotPassword) {

        User user = forgotPassword.getUser();
        user.setPasswordHash(new BCryptPasswordEncoder().encode(form.getPassword()));
        userRepository.save(user);

        forgotPassword.setValidEntity(ValidEntity.INVALID);
        forgotPasswordRepository.save(forgotPassword);
        return user;
    }
}
