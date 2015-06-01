package net.study.controller;

import net.study.domain.User;
import net.study.repository.UserRepository;
import net.study.service.user.UserService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 5/31/15 | 1:49 PM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

@Controller
public class UsersController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/users")
    public String getUsersPage(Model model,
                                     @RequestParam(value = "p", required = false) Integer requestPage) {
        LOGGER.debug("Getting users page");

        if(requestPage == null) requestPage = 1;

        int totalCount = (int)userRepository.count();

        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(0, 10, sort);
        Page<User> users = userRepository.findAll(pageable);
        List<User> userList = users.getContent();

        model.addAttribute("userList", userList);

        Paging paging = new Paging().paging(requestPage, 10, totalCount);
        model.addAttribute("paging", paging);

        /*
        유저가 없을 때 수행된다.
         */
        if(totalCount == 0) {
            model.addAttribute("hasUser", false);
        } else {
            model.addAttribute("hasUser", true);
        }

        return "user/users";
    }
}