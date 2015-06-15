package net.study.controller;

import net.study.domain.Comment;
import net.study.domain.CurrentUser;
import net.study.repository.BoardRepository;
import net.study.repository.CommentRepository;
import net.study.service.comment.CommentService;
import net.study.service.sequence.SequenceService;
import net.study.util.Paging;
import net.study.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/2/15 | 4:14 PM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

@Controller
@RequestMapping(value = "/comment")
public class CommentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);

    private final int PAGE_PER_SIZE = 10;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentService commentService;

    @Autowired
    private SequenceService sequenceService;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private Utils utils;

    @RequestMapping("/list/{boardId}")
    public String commentList(@PathVariable("boardId") Long boardId,
                              @RequestParam(value = "p", required = false) Integer requestPage,
                              Model model){
        LOGGER.debug("Getting comment list page for board id = {}, page number={}", boardId, requestPage);

        if(requestPage == null) requestPage = 1;

        int totalCount = (int)commentRepository.countByBoardId(boardId);

        Sort sort = new Sort(Sort.Direction.DESC, "sequenceNumber");
        Pageable pageable = new PageRequest(requestPage-1, PAGE_PER_SIZE, sort);
        Page<Comment> comments = commentService.findByBoardId(boardId, pageable);
        List<Comment> commentList = comments.getContent();

        model.addAttribute("commentList", commentList);

        Paging paging = new Paging().paging(requestPage, PAGE_PER_SIZE, totalCount);
        model.addAttribute("paging", paging);

        if(totalCount == 0) {
            model.addAttribute("hasComment", false);
        } else {
            model.addAttribute("hasComment", true);
        }

        model.addAttribute("boardId", boardId);

        return "comment/list";
    }

    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String commentWrite(@ModelAttribute("currentUser")CurrentUser currentUser,
                               @RequestParam(value = "boardId", required = true) Long boardId,
                               @RequestParam(value = "content", required = true ) String content){
        LOGGER.debug("Getting comment write for board id={}, content={}", boardId, content);

        long groupId = sequenceService.generateNextGroupNumber("comment");

        Comment comment = new Comment();

        comment.setGroupId(groupId);
        DecimalFormat decimalFormat = new DecimalFormat("0000000000");
        comment.setSequenceNumber(decimalFormat.format(groupId) + "99");
        comment.setPostingDate(new Date());
        comment.setContent(content);
        comment.setUser(currentUser.getUser());
        comment.setBoard(boardRepository.findOne(boardId));


        commentRepository.save(comment);

        return "common/success";
    }

    @RequestMapping("/update/{commentId}")
    public String commentUpdate(@ModelAttribute("currentUser")CurrentUser currentUser,
                                @PathVariable("commentId") Long commentId,
                                Model model){
        LOGGER.debug("Getting comment update form for id={}", commentId);

        Comment comment = commentRepository.findOne(commentId);

        if(!comment.checkUser(currentUser.getUser())){
            new UsernameNotFoundException("Username Not Equal");
        }

        model.addAttribute("comment", comment);

        return "comment/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String commentUpdate(@ModelAttribute("currentUser")CurrentUser currentUser,
                                @ModelAttribute("commentId") Long commentId,
                                @RequestParam(value = "content", required = true ) String content){
        LOGGER.debug("Getting comment update for id={}, content={}", commentId, content);

        Comment comment = commentRepository.findOne(commentId);

        if(!comment.checkUser(currentUser.getUser())){
            new UsernameNotFoundException("Username Not Equal");
        }

        if(!comment.getContent().equals(content)) comment.setContent(content);

        commentRepository.save(comment);

        return "common/success";
    }

    @RequestMapping("/reply/{parentCommentId}")
    public String commentReply(@PathVariable("parentCommentId") Long parentCommentId,
                               Model model) throws Exception{
        LOGGER.debug("Getting comment reply form for parent id={}", parentCommentId);

        Comment parent = commentRepository.findOne(parentCommentId);

        utils.checkParent(parent, parentCommentId);

        String searchMaxSeqNum = parent.getSequenceNumber();
        String searchMinSeqNum = utils.getSearchMinSeqNum(parent);

        String lastChildSeq = commentRepository.findBySequenceNumber(searchMaxSeqNum, searchMinSeqNum);
        LOGGER.debug("Getting lastChildSeq ={}", lastChildSeq);

        String sequenceNumber = utils.getSequenceNumber(parent, lastChildSeq);

        model.addAttribute("parent", parent);

        return "comment/reply";
    }

    @RequestMapping(value = "/reply", method = RequestMethod.POST)
    public String commentReply(@ModelAttribute("currentUser")CurrentUser currentUser,
                               @RequestParam("parentCommentId") Long parentCommentId,
                               @RequestParam(value = "content", required = true ) String content) throws Exception{
        LOGGER.debug("Getting comment reply for parent id={}, content={}", parentCommentId, content);

        Comment parent = commentRepository.findOne(parentCommentId);

        utils.checkParent(parent, parentCommentId);

        String searchMaxSeqNum = parent.getSequenceNumber();
        String searchMinSeqNum = utils.getSearchMinSeqNum(parent);

        String lastChildSeq = commentRepository.findBySequenceNumber(searchMaxSeqNum, searchMinSeqNum);
        LOGGER.debug("Getting lastChildSeq ={}", lastChildSeq);

        String sequenceNumber = utils.getSequenceNumber(parent, lastChildSeq);

        Comment comment = new Comment();
        comment.setGroupId(parent.getGroupId());
        comment.setSequenceNumber(sequenceNumber);
        comment.setPostingDate(new Date());
        comment.setContent(content);
        comment.setUser(currentUser.getUser());
        comment.setBoard(boardRepository.findOne(parent.getBoard().getId()));

        commentRepository.save(comment);

        return "common/success";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String boardDelete(@ModelAttribute("currentUser")CurrentUser currentUser,
                              @ModelAttribute("commentId") Long commentId){
        LOGGER.debug("Getting comment delete for id={}", commentId);

        Comment comment = commentRepository.findOne(commentId);

        if(!comment.checkUser(currentUser.getUser())){
            new UsernameNotFoundException("Username Not Equal");
        }

        commentRepository.delete(comment);

        return "common/success";
    }

}
