package net.study.controller;

import net.study.domain.Board;
import net.study.domain.CurrentUser;
import net.study.repository.BoardRepository;
import net.study.util.Paging;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 5/26/15 | 7:48 PM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

@Controller
@RequestMapping(value = "/article")
public class BoardController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BoardController.class);

    private final int PAGE_PER_SIZE = 10;

    @Autowired
    private BoardRepository boardRepository;

    @RequestMapping(value = "/list")
    public String boardList(Model model,
                            @RequestParam(value = "p", required = false) Integer requestPage){
        LOGGER.debug("Getting board list page for page number={}", requestPage);

        if(requestPage == null) requestPage = 1;

        int totalCount = (int)boardRepository.count();

        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(requestPage-1, PAGE_PER_SIZE, sort);
        Page<Board> boards = boardRepository.findAll(pageable);
        List<Board> boardList = boards.getContent();

        model.addAttribute("boardList", boardList);

        Paging paging = new Paging().paging(requestPage, PAGE_PER_SIZE, totalCount);
        model.addAttribute("paging", paging);

        if(totalCount == 0) {
            model.addAttribute("hasBoard", false);
        } else {
            model.addAttribute("hasBoard", true);
        }

        return "board/list";
    }

    @RequestMapping(value = "/write")
    public String boardWrite() throws Exception{
        LOGGER.debug("Getting board write form");

        return "board/write";
    }

    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String boardWrite(@ModelAttribute("currentUser")CurrentUser currentUser,
                             @RequestParam(value = "title", required = true) String title,
                             @RequestParam(value = "content", required = true ) String content){
        LOGGER.debug("Getting board write for title={}, content={}", title, content);

        Board board = new Board();
        board.setTitle(title);
        board.setContent(content);
        board.setPostingDate(new Date());
        board.setUser(currentUser.getUser());

        boardRepository.save(board);

        return "redirect:/article/list";
    }

    @RequestMapping(value = "/read/{boardId}")
    public String boardRead(@PathVariable("boardId") Long boardId,
                            Model model){
        LOGGER.debug("Getting board read page for id={}", boardId);

        Board board = boardRepository.findOne(boardId);

        board.setReadCount(board.getReadCount()+1);
        boardRepository.save(board);
        model.addAttribute("board", board);

        return "board/read";
    }

    @RequestMapping(value = "/update/{boardId}")
    public String boardUpdate(@ModelAttribute("currentUser")CurrentUser currentUser,
                              @PathVariable("boardId") Long boardId,
                              Model model){
        LOGGER.debug("Getting board update form for id={}", boardId);

        Board board = boardRepository.findOne(boardId);

        if(!board.checkUser(currentUser.getUser())){
            return "redirect:/article/read/"+boardId;
        }

        model.addAttribute("board", board);

        return "board/update";
    }

    @RequestMapping(value = "/update/{boardId}", method = RequestMethod.POST)
    public String boardUpdate(@ModelAttribute("currentUser")CurrentUser currentUser,
                              @PathVariable("boardId") Long boardId,
                              @RequestParam(value = "title", required = true) String title,
                              @RequestParam(value = "content", required = true ) String content){
        LOGGER.debug("Getting board update for id={}, title={}, content={}", boardId, title, content);

        Board board = boardRepository.findOne(boardId);

        if(!board.checkUser(currentUser.getUser())){
            return "redirect:/article/read/"+boardId;
        }

        if(!board.getTitle().equals(title)) board.setTitle(title);
        if(!board.getContent().equals(content)) board.setContent(content);

        boardRepository.save(board);

        return "redirect:/article/read/"+boardId;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String boardDelete(@ModelAttribute("currentUser")CurrentUser currentUser,
                              @RequestParam(value = "boardId", required = true) Long boardId){
        LOGGER.debug("Getting board delete for id={}", boardId);

        Board board = boardRepository.findOne(boardId);

        if(!board.checkUser(currentUser.getUser())){
            return "redirect:/article/read/"+boardId;
        }

        boardRepository.delete(board);

        return "redirect:/article/list";
    }
}
