package net.study.repository;

import net.study.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/5/15 | 9:50 PM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

public interface StudyRepository extends JpaRepository<Study, Long> {
}
