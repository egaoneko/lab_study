package net.study.service.comment;

import net.study.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/4/15 | 1:19 AM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

public interface CommentService {

    public Page<Comment> findByBoardId(Long boardId, Pageable pageable);
}
