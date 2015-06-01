package net.study.controller;

import net.study.domain.UserCreateForm;
import net.study.domain.validator.UserCreateFormValidator;
import net.study.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.NoSuchElementException;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 5/30/15 | 6:03 PM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

@Controller
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final UserCreateFormValidator userCreateFormValidator;

    @Autowired
    public UserController(UserService userService, UserCreateFormValidator userCreateFormValidator) {
        this.userService = userService;
        this.userCreateFormValidator = userCreateFormValidator;
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userCreateFormValidator);
    }

    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @RequestMapping("/user/{id}")
    public ModelAndView getUserPage(@PathVariable Long id) {
        LOGGER.debug("Getting user page for user={}", id);
        return new ModelAndView("user/user", "user", userService.getUserById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("User=%s not found", id))));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/user/create", method = RequestMethod.GET)
    public ModelAndView getUserCreatePage() {
        LOGGER.debug("Getting user create form");
        return new ModelAndView("user/create", "form", new UserCreateForm());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public String handleUserCreateForm(@Valid @ModelAttribute("form") UserCreateForm form, BindingResult bindingResult) {
        LOGGER.debug("Processing user create form={}, bindingResult={}", form, bindingResult);
        if (bindingResult.hasErrors()) {
            // failed validation
            return "user/create";
        }
        try {
            userService.create(form);
        } catch (DataIntegrityViolationException e) {
            // probably email already exists - very rare case when multiple admins are adding same user
            // at the same time and form validation has passed for more than one of them.
            LOGGER.warn("Exception occurred when trying to save the user, assuming duplicate email", e);
            bindingResult.reject("email.exists", "Email already exists");
            return "user/create";
        }
        // ok, redirect
        return "redirect:/users";
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.GET)
    public ModelAndView getUserRegisterPage() {
        LOGGER.debug("Getting user register form");
        return new ModelAndView("user/register", "form", new UserCreateForm());
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public String handleUserRegisterForm(@Valid @ModelAttribute("form") UserCreateForm form, BindingResult bindingResult) {
        LOGGER.debug("Processing user register form={}, bindingResult={}", form, bindingResult);
        if (bindingResult.hasErrors()) {
            // failed validation
            return "user/register";
        }
        try {
            userService.create(form);
        } catch (DataIntegrityViolationException e) {
            // probably email already exists - very rare case when multiple admins are adding same user
            // at the same time and form validation has passed for more than one of them.
            LOGGER.warn("Exception occurred when trying to save the user, assuming duplicate email", e);
            bindingResult.reject("email.exists", "Email already exists");
            return "user/register";
        }
        // ok, redirect
        return "redirect:/";
    }

}


//@Controller
//@RequestMapping(value = "/user")
//public class UserController {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//    /*
//    유저 리스트
//     */
//    @RequestMapping(value = "/list")
//    public String userList(Model model,
//                            @RequestParam(value = "p", required = false) Integer requestPage){
//
//        if(requestPage == null) requestPage = 1;
//
//        int totalCount = (int)userRepository.count();
//
//        Sort sort = new Sort(Sort.Direction.DESC, "id");
//        Pageable pageable = new PageRequest(0, 10, sort);
//        Page<User> users = userRepository.findAll(pageable);
//        List<User> userList = users.getContent();
//
//        model.addAttribute("userList", userList);
//
//        Paging paging = new Paging().paging(requestPage, 10, totalCount);
//        model.addAttribute("paging", paging);
//
//        /*
//        유저가 없을 때 수행된다.
//         */
//        if(totalCount == 0) {
//            model.addAttribute("hasUser", false);
//        } else {
//            model.addAttribute("hasUser", true);
//        }
//
//        return "user/list";
//    }
//
//    /*
//    유저 등록
//     */
//    @RequestMapping("/register")
//    public String userRegister(){
//        return "user/register";
//    }
//
//    @RequestMapping(value= "/register", method=RequestMethod.POST)
//    public String userRegister(@RequestParam("name")String name,
//                               @RequestParam("login")String login,
//                               @RequestParam("password")String password,
//                               Model model){
//
//        int intcode;
//
//        if(name.isEmpty() || login.isEmpty() || password.isEmpty()){
//            model.addAttribute("error", "필수항목을 채워주세요.");
//            return "redirect:/user/register";
//        }
//
//        if(name.length() < 3 || password.length()<3|| login.length()<6){
//            model.addAttribute("error", "잘못된 입력입니다.");
//            return "redirect:/user/register";
//        }
//
//        if (userRepository.findByLogin(login) != null){
//            model.addAttribute("error", "이미 사용하고 있는 이메일입니다.");
//            return "redirect:/user/register";
//        }
//
//        Set<Role> roles = new HashSet<Role>();
//        roles.add(roleRepository.findByName("ROLE_USER"));
//
//        User user = new User(name,login,password,roles);
//        userRepository.save(user);
//
//        return "redirect:/";
//    }
//
//    /*
//    ajax check Email
//     */
//    @RequestMapping(value="/checkEmail.do", method= RequestMethod.POST)
//    public void checkEmail(@RequestParam("email") String email,
//                           HttpServletResponse response) throws Exception {
//
//        if(email.length() < 3 || email.isEmpty()){
//            response.getWriter().print("404");
//            return;
//        }
//
//        if (userRepository.findByLogin(email) != null){
//            response.getWriter().print("400");
//            return;
//        }else{
//            response.getWriter().print("You can use this email");
//        }
//
//    }
//
//}
