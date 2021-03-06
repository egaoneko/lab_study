package net.study.controller;

import net.study.domain.CurrentUser;
import net.study.domain.Study;
import net.study.domain.User;
import net.study.domain.enums.Status;
import net.study.domain.form.StudyCreateForm;
import net.study.domain.validator.StudyCreateFormValidator;
import net.study.repository.BookRepository;
import net.study.repository.StudyRepository;
import net.study.repository.UserRepository;
import net.study.service.study.StudyService;
import net.study.util.Paging;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/5/15 | 9:50 PM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

@Controller
@RequestMapping("/study")
public class StudyController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudyController.class);

    private final int PAGE_PER_SIZE = 10;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private StudyRepository studyRepository;

    @Autowired
    private StudyService studyService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudyCreateFormValidator studyCreateFormValidator;

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(studyCreateFormValidator);
    }

    @RequestMapping("/list")
    public String studyList(Model model,
                            @RequestParam(value = "p", required = false) Integer requestPage){
        LOGGER.debug("Getting study list page for page number={}", requestPage);

        if(requestPage == null) requestPage = 1;

        int totalCount = (int)studyRepository.count();

        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(requestPage-1, PAGE_PER_SIZE, sort);
        Page<Study> studies = studyRepository.findAll(pageable);
        List<Study> studyList = studies.getContent();

        model.addAttribute("studyList", studyList);

        Paging paging = new Paging().paging(requestPage, PAGE_PER_SIZE, totalCount);
        model.addAttribute("paging", paging);

        if(totalCount == 0) {
            model.addAttribute("hasStudy", false);
        } else {
            model.addAttribute("hasStudy", true);
        }

        return "study/list";
    }

    @RequestMapping("/write")
    public ModelAndView studyWrite() throws Exception{
        LOGGER.debug("Getting study write form");
        return new ModelAndView("study/write", "form", new StudyCreateForm());
    }

    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String studyWrite(@Valid @ModelAttribute("form") StudyCreateForm form, BindingResult bindingResult){
        LOGGER.debug("Processing study create form={}, bindingResult={}", form, bindingResult);

        /*
        For remaining books when error occurred.
         */
        if(form.getBooks() != null){
            form.setBookSet(form.getBooks().stream().map(bookRepository::findOne).collect(Collectors.toSet()));
        }

        if (bindingResult.hasErrors()) {
            // failed validation
            return "study/write";
        }
        try {
            studyService.create(form);
        } catch (DataIntegrityViolationException e) {
            LOGGER.warn("Unknown Error", e);
            bindingResult.reject("unknown.error", "Unknown Error");
            return "study/write";
        }
        // ok, redirect
        return "redirect:/study/list";
    }

    @RequestMapping("/read/{studyId}")
    public String studyRead(@PathVariable("studyId") Long studyId,
                            HttpServletRequest request,
                            Model model){
        LOGGER.debug("Getting study read page for id={}", studyId);

        Study study = studyRepository.findOne(studyId);
        model.addAttribute("study", study);

        String referer = request.getHeader("Referer");
        LOGGER.debug("Getting Referer = {}", referer);
        if(referer.contains("/study/list")){
            return "study/read";
        }

        return "study/readLayout";
    }

    @RequestMapping("/update/{studyId}")
    public ModelAndView studyUpdate(@ModelAttribute("currentUser")CurrentUser currentUser,
                                    @PathVariable("studyId") Long studyId){
        LOGGER.debug("Getting study update form for id={}", studyId);

        Study study = studyRepository.findOne(studyId);

        if(!study.checkUser(currentUser.getUser())){
            return new ModelAndView("redirect:/study/read/"+studyId);
        }

        return new ModelAndView("study/update", "form", studyService.convertStudyToStudyCreateForm(study));
    }

    @RequestMapping(value = "/update/{studyId}", method = RequestMethod.POST)
    public String studyUpdate(@ModelAttribute("currentUser")CurrentUser currentUser,
                              @Valid @ModelAttribute("form") StudyCreateForm form, BindingResult bindingResult){
        LOGGER.debug("Processing study update form={}, bindingResult={}", form, bindingResult);

        Study study = studyRepository.findOne(form.getId());

        /*
        For remaining books when error occurred.
         */
        if(!study.checkUser(currentUser.getUser())){
            return "redirect:/study/read/"+form.getId();
        }

        if(form.getBooks() != null){
            form.setBookSet(form.getBooks().stream().map(bookRepository::findOne).collect(Collectors.toSet()));
        }

        if (bindingResult.hasErrors()) {
            // failed validation
            return "study/update";
        }
        try {
            studyService.update(form);
        } catch (DataIntegrityViolationException e) {
            LOGGER.warn("Unknown Error", e);
            bindingResult.reject("unknown.error", "Unknown Error");
            return "study/update";
        }
        // ok, redirect
        return "redirect:/study/read/"+form.getId();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String studyDelete(@ModelAttribute("currentUser")CurrentUser currentUser,
                              @RequestParam(value = "studyId", required = true) Long studyId){
        LOGGER.debug("Getting study delete for id={}", studyId);

        Study study = studyRepository.findOne(studyId);

        if(!study.checkUser(currentUser.getUser())){
            return "redirect:/study/read/"+studyId;
        }

        studyRepository.delete(study);

        return "redirect:/study/list";
    }

    @RequestMapping("/json")
    @ResponseBody
    public Study studyJson(@RequestParam(value = "id", required = true) Long id){
        LOGGER.debug("Getting study json for id={}", id);
        return studyRepository.findOne(id);
    }

    @RequestMapping("/users/{studyId}")
    public ModelAndView studyUsers(@ModelAttribute("currentUser")CurrentUser currentUser,
                                   @PathVariable("studyId") Long studyId){
        Study study =  studyRepository.findOne(studyId);
        Set<User> users = study.getParticipants();

        for(User user : users){
            if(user.getId() == currentUser.getId()){
                ModelAndView modelAndView = new ModelAndView("study/users");
                modelAndView.addObject("users", users);
                modelAndView.addObject("owner", study.getUser());
                return modelAndView;
            }
        }

        return new ModelAndView("redirect:/study/read/"+studyId);
    }

    @RequestMapping(value = "/users/{studyId}", method = RequestMethod.POST)
    public String leaveStudy(@ModelAttribute("currentUser")CurrentUser currentUser,
                             @PathVariable("studyId") Long studyId,
                             @RequestParam(value = "userId", required = true) Long userId){
        User user = userRepository.findOne(userId);
        Study study = studyRepository.findOne(studyId);

        if(userId == currentUser.getId() || currentUser.getUser().getId() == study.getUser().getId()){
            Set<User> userSet = study.getParticipants();
            userSet.remove(user);
            if(userSet.size() < study.getParticipant()){
                if(study.getStatus().equals(Status.EXCESS)){
                    study.setStatus(Status.OPEN);
                }
            }
            studyRepository.save(study);
            return "redirect:/study/read/"+studyId;
        }
        return "redirect:/study/read/"+studyId;
    }
}
