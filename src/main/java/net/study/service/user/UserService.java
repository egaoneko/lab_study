package net.study.service.user;

import net.study.domain.User;
import net.study.domain.UserCreateForm;
import net.study.domain.UserUpdatePasswordForm;

import java.util.Collection;
import java.util.Optional;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 5/31/15 | 1:41 PM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

public interface UserService {

    Optional<User> getUserById(long id);

    Optional<User> getUserByEmail(String email);

    Collection<User> getAllUsers();

    User create(UserCreateForm form);

    User updatePassword(UserUpdatePasswordForm form);

}
