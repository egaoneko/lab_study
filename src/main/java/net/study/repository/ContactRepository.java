package net.study.repository;

import net.study.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/18/15 | 4:23 AM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
