package net.study.repository;

import net.study.domain.ForgotPassword;
import net.study.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import java.util.List;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/14/15 | 10:14 PM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

public interface ForgotPasswordRepository extends JpaRepository<ForgotPassword, Long>, QueryDslPredicateExecutor<ForgotPassword> {

    ForgotPassword findOneByKeyHash(String keyHash);

}
