package net.study.service.comment;

import com.mysema.query.BooleanBuilder;
import net.study.domain.Comment;
import net.study.domain.QComment;
import net.study.repository.CommentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/4/15 | 1:20 AM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

@Service
public class CommentServiceImpl implements CommentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentServiceImpl.class);
    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Page<Comment> findByBoardId(Long boardId, Pageable pageable) {

        LOGGER.debug("Find Comment Page By board id = {}", boardId);

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(QComment.comment.board.id.eq(boardId));

        return commentRepository.findAll(builder, pageable);
    }
}
