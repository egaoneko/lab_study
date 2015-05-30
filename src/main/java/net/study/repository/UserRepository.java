package net.study.repository;

import net.study.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 5/30/15 | 5:57 PM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);
}