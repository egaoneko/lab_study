package net.study.repository;

import net.study.domain.Sequence;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/2/15 | 9:48 PM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

public interface SequenceRepository extends JpaRepository<Sequence, Long> {

    Sequence findOneByName(String name);
}
