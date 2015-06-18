package net.study.controller;

import net.study.domain.CurrentUser;
import net.study.domain.Message;
import net.study.domain.Study;
import net.study.domain.User;
import net.study.domain.enums.MessageStatus;
import net.study.domain.enums.Status;
import net.study.domain.enums.StudyRequest;
import net.study.error.ResourceNotFoundException;
import net.study.repository.MessageRepository;
import net.study.repository.StudyRepository;
import net.study.repository.UserRepository;
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
import java.util.Set;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/18/15 | 8:05 PM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

@Controller
@RequestMapping("/message")
public class MessageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

    private final int PAGE_PER_SIZE = 10;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private StudyRepository studyRepository;

    //@RequestMapping("/send")
    public String messageForm(){
        LOGGER.debug("Send message form");

        return "message/send";
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    @ResponseBody
    public Message messageSend(@ModelAttribute("currentUser")CurrentUser currentUser,
                               @RequestParam(value = "message", required = true) String message,
                               @RequestParam(value = "receiverId", required = true) Long receiverId,
                               @RequestParam(value = "messageStatus", required = true) String messageStatus,
                               @RequestParam(value = "studyId",required = true) Long studyId) throws Exception{


        User receiver = userRepository.findOne(receiverId);

        Message sendMessage = new Message(message, currentUser.getUser(), receiver, new Date(), MessageStatus.valueOf(messageStatus), StudyRequest.UNDETERMINED, studyRepository.findOne(studyId));
        LOGGER.debug("Send message sender={} to receiver={}, message={}, status={}", currentUser.getName(), receiver.getName(), message, MessageStatus.valueOf(messageStatus) );

        sendMessage = messageRepository.save(sendMessage);

        return sendMessage;
    }

    @RequestMapping("/read/{messageId}")
    public String messageRead(@PathVariable("messageId") Long messageId,
                              @ModelAttribute("currentUser")CurrentUser currentUser,
                              Model model){
        LOGGER.debug("Read message messageId={}", messageId);

        Message message = messageRepository.findOne(messageId);

        if(!message.checkUser(currentUser.getUser())){
            throw new ResourceNotFoundException();
        }

        if(message.getReceiveDate() == null) {
            message.setReceiveDate(new Date());
            message = messageRepository.save(message);
        }

        model.addAttribute("message", message);
        return "message/read";
    }

    @RequestMapping("/list/receive")
    public String messageReceiveList(@ModelAttribute("currentUser")CurrentUser currentUser,
                                     Model model,
                                     @RequestParam(value = "p", required = false) Integer requestPage){
        LOGGER.debug("Getting board list page for page number={}", requestPage);

        if(requestPage == null) requestPage = 1;

        int totalCount = (int)messageRepository.countByReceiverId(currentUser.getUser().getId());

        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(requestPage-1, PAGE_PER_SIZE, sort);
        Page<Message> messages = messageRepository.findAllByReceiverId(pageable, currentUser.getUser().getId());
        List<Message> messageList = messages.getContent();

        model.addAttribute("messageList", messageList);

        Paging paging = new Paging().paging(requestPage, PAGE_PER_SIZE, totalCount);
        model.addAttribute("paging", paging);

        if(totalCount == 0) {
            model.addAttribute("hasMessage", false);
        } else {
            model.addAttribute("hasMessage", true);
        }

        model.addAttribute("byWhom", "receiver");

        return "message/list";
    }

    @RequestMapping("/list/send")
    public String messageSendList(@ModelAttribute("currentUser")CurrentUser currentUser,
                                  Model model,
                                  @RequestParam(value = "p", required = false) Integer requestPage){
        LOGGER.debug("Getting board list page for page number={}", requestPage);

        if(requestPage == null) requestPage = 1;

        int totalCount = (int) messageRepository.countBySenderId(currentUser.getUser().getId());

        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(requestPage-1, PAGE_PER_SIZE, sort);
        Page<Message> messages = messageRepository.findAllBySenderId(pageable, currentUser.getUser().getId());
        List<Message> messageList = messages.getContent();

        model.addAttribute("messageList", messageList);

        Paging paging = new Paging().paging(requestPage, PAGE_PER_SIZE, totalCount);
        model.addAttribute("paging", paging);

        if(totalCount == 0) {
            model.addAttribute("hasMessage", false);
        } else {
            model.addAttribute("hasMessage", true);
        }

        model.addAttribute("byWhom", "sender");

        return "message/list";
    }

    @RequestMapping(value = "/accept", method = RequestMethod.POST)
    @ResponseBody
    public Message acceptJoin(@RequestParam(value = "studyId", required = true) Long studyId,
                             @RequestParam(value = "userId", required = true) Long userId,
                             @RequestParam(value = "messageId", required = true) Long messageId){

        Study study = studyRepository.findOne(studyId);
        User user = userRepository.findOne(userId);
        Message message = messageRepository.findOne(messageId);

        Set<User> userSet = study.getParticipants();

        if(userSet.size() >= study.getParticipant()){
            if(!study.getStatus().equals(Status.EXCESS)||!study.getStatus().equals(Status.CLOSE)){
                study.setStatus(Status.EXCESS);
            }
            return null;
        }

        userSet.add(user);
        study.setParticipants(userSet);
        if(userSet.size() == study.getParticipant()){
            study.setStatus(Status.EXCESS);
        }
        studyRepository.save(study);

        message.setStudyRequest(StudyRequest.ACCEPT);
        messageRepository.save(message);

        return message;
    }

    @RequestMapping(value = "/reject", method = RequestMethod.POST)
    @ResponseBody
    public Message rejectJoin(@RequestParam(value = "messageId", required = true) Long messageId){

        Message message = messageRepository.findOne(messageId);
        message.setStudyRequest(StudyRequest.REJECT);
        messageRepository.save(message);

        return message;
    }

}
