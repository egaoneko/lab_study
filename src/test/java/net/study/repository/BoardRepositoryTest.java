package net.study.repository;

import net.study.Application;
import net.study.domain.Board;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 5/28/15 | 3:27 PM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes= Application.class)
@Transactional
public class BoardRepositoryTest {

    private static final String HELLO_WORLD = "hello world";

    @Autowired
    private BoardRepository boardRepository;

    @Before
    public void setUp() throws Exception {
        Board board = new Board();
        board.setTitle(HELLO_WORLD);
        board.setContent(HELLO_WORLD);
        board.setPostingDate(new Date());
        boardRepository.save(board);
        assertEquals(boardRepository.count(), 1);
    }

    @Test
    public void testEdit() throws Exception {
        List<Board> boards = boardRepository.findAll();
        Board board = boards.get(boards.size()-1);
        assertEquals(board.getContent(), HELLO_WORLD);

        board.setContent("test");
        boardRepository.save(board);
        Board getBoard = boardRepository.getOne(board.getId());
        assertEquals(getBoard.getContent(), board.getContent());
    }

    @Test
    public void testDelete() throws Exception {
        boardRepository.deleteAll();
        assertEquals(boardRepository.count(), 0);
    }
}
