package net.study.repository;

import net.study.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 5/30/15 | 10:15 PM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
