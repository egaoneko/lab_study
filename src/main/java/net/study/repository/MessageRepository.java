package net.study.repository;

import net.study.domain.Message;
import net.study.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/18/15 | 8:04 PM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

public interface MessageRepository extends JpaRepository<Message, Long> {

    long countByReceiverId(Long receiverId);
    long countBySenderId(Long senderId);

    Page<Message> findAllByReceiverId(Pageable pageable, Long receiverId);
    Page<Message> findAllBySenderId(Pageable pageable, Long senderId);
}
