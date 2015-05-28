package net.study.controller;

import net.study.domain.Board;
import net.study.error.BoardNotFoundException;
import net.study.repository.BoardRepository;
import net.study.util.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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

@SpringBootApplication
@Controller
@RequestMapping(value = "/article")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    /*
    게시판 리스트
     */
    @RequestMapping(value = "/list")
    public String boardList(Model model,
                            @RequestParam(value = "p", required = false) Integer requestPage) throws Exception{

        if(requestPage == null) requestPage = 1;
        //if(requestPage <= 0) return (ModelAndView)new ModelAndView("redirect:/board/list.do");

        int totalCount = (int)boardRepository.count();

        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(0, 10, sort);
        Page<Board> boards = boardRepository.findAll(pageable);
        List<Board> boardList = boards.getContent();

        model.addAttribute("boardList", boardList);

        Paging paging = new Paging().paging(requestPage, 10, totalCount);
        model.addAttribute("paging", paging);

        /*
        게시글이 없을 때 수행된다.
         */
        if(totalCount == 0) {
            model.addAttribute("hasBoard", false);
        } else {
            model.addAttribute("hasBoard", true);
        }

        return "board/list";
    }

    /*
    게시판 글쓰기 폼
     */
    @RequestMapping(value = "/write")
    public String boardWrite(Model model) throws Exception{

//        util.isMemberId(auth);
        return "board/writeForm";
    }

    /*
    게시판 글쓰기
     */
    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String boardWrite(@RequestParam(value = "title", required = true) String title,
                             @RequestParam(value = "content", required = true ) String content) throws Exception{


//        String memberId = util.isMemberId(auth);

        Board board = new Board();
        board.setTitle(title);
        board.setContent(content);
        board.setPostingDate(new Date());

        boardRepository.save(board);

        return "redirect:/article/list";
    }

    /*
    게시판 읽기
     */
    @RequestMapping(value = "/read/{boardId}")
    public String boardRead(@PathVariable("boardId") Long boardId,
                            Model model) throws Exception {

        Board board = boardRepository.findOne(boardId);

        if(board == null) throw new BoardNotFoundException("게시글이 존재하지 않음 : " + boardId);

        board.setReadCount(board.getReadCount()+1);
        boardRepository.save(board);
        model.addAttribute("board", board);

        return "board/read";
    }

    /*
    게시판 수정 폼
     */
    @RequestMapping(value = "/update/{boardId}")
    public String boardUpdate(@PathVariable("boardId") Long boardId,
                              Model model) throws Exception{

        //String memberId = util.isMemberId(auth);

        Board board = boardRepository.findOne(boardId);
        if(board == null) throw new BoardNotFoundException("게시글이 존재하지 않음 : " + boardId);

        //util.isEqualMemberId(boardVO.getUserEmail(), memberId);

        model.addAttribute("board", board);

        return "board/updateForm";
    }

    /*
    게시판 수정
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String boardUpdate(@RequestParam(value = "boardId", required = true) Long boardId,
                              @RequestParam(value = "title", required = true) String title,
                              @RequestParam(value = "content", required = true ) String content) throws Exception {

//        String memberId = util.isMemberId(auth);

        Board board = boardRepository.findOne(boardId);
        if(board == null) throw new BoardNotFoundException("게시글이 존재하지 않음 : " + boardId);

//        util.isEqualMemberId(boardVO.getUserEmail(), memberId);

        if(!board.getTitle().equals(title)) board.setTitle(title);
        if(!board.getContent().equals(content)) board.setContent(content);

        boardRepository.save(board);

        return "redirect:/article/read/"+boardId;
    }

    /*
    게시판 삭제
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String boardDelete(@RequestParam(value = "boardId", required = true) Long boardId) throws Exception {

//        String memberId = util.isMemberId(auth);


        Board board = boardRepository.findOne(boardId);

//        util.isEqualMemberId(boardVO.getUserEmail(), memberId);

        boardRepository.delete(board);

        return "redirect:/article/list";
    }
}
