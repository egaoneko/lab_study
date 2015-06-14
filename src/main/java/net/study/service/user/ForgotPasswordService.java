package net.study.service.user;

import net.study.domain.ForgotPassword;
import net.study.domain.User;
import net.study.domain.enums.ValidEntity;
import net.study.domain.form.ForgotPasswordForm;

import java.util.List;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/14/15 | 10:17 PM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

public interface ForgotPasswordService {

    Iterable<ForgotPassword> findAllByUserANDBYValidEntity(User user, ValidEntity validEntity);

    ForgotPassword create(User user);

    User resetPassword(ForgotPasswordForm form, ForgotPassword forgotPassword);
}
