package net.study.controller;

import net.study.domain.Role;
import net.study.domain.User;
import net.study.repository.RoleRepository;
import net.study.repository.UserRepository;
import net.study.util.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 5/30/15 | 6:03 PM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    /*
    유저 리스트
     */
    @RequestMapping(value = "/list")
    public String userList(Model model,
                            @RequestParam(value = "p", required = false) Integer requestPage){

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

        return "user/list";
    }

    /*
    유저 등록
     */
    @RequestMapping("/register")
    public String userRegister(){
        return "user/register";
    }

    @RequestMapping(value= "/register", method=RequestMethod.POST)
    public String userRegister(@RequestParam("name")String name,
                               @RequestParam("login")String login,
                               @RequestParam("password")String password,
                               Model model){

        int intcode;

        if(name.isEmpty() || login.isEmpty() || password.isEmpty()){
            model.addAttribute("error", "필수항목을 채워주세요.");
            return "redirect:/user/register";
        }

        if(name.length() < 3 || password.length()<3|| login.length()<6){
            model.addAttribute("error", "잘못된 입력입니다.");
            return "redirect:/user/register";
        }

        if (userRepository.findByLogin(login) != null){
            model.addAttribute("error", "이미 사용하고 있는 이메일입니다.");
            return "redirect:/user/register";
        }

        Set<Role> roles = new HashSet<Role>();
        roles.add(roleRepository.findByName("ROLE_USER"));

        User user = new User(name,login,password,roles);
        userRepository.save(user);

        return "redirect:/";
    }

    /*
    ajax check Email
     */
    @RequestMapping(value="/checkEmail.do", method= RequestMethod.POST)
    public void checkEmail(@RequestParam("email") String email,
                           HttpServletResponse response) throws Exception {

        if(email.length() < 3 || email.isEmpty()){
            response.getWriter().print("404");
            return;
        }

        if (userRepository.findByLogin(email) != null){
            response.getWriter().print("400");
            return;
        }else{
            response.getWriter().print("You can use this email");
        }

    }

}
