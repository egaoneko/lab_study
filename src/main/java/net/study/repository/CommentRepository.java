package net.study.repository;

import net.study.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.query.Param;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/2/15 | 9:22 PM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

public interface CommentRepository extends JpaRepository<Comment, Long>, QueryDslPredicateExecutor<Comment> {

    @Query("select min(c.sequenceNumber) from Comment c where c.sequenceNumber < :searchMaxSeqNum " +
            "and c.sequenceNumber >= :searchMinSeqNum")
    String findBySequenceNumber(@Param("searchMaxSeqNum") String searchMaxSeqNum,
                                @Param("searchMinSeqNum") String searchMinSeqNum);
}
