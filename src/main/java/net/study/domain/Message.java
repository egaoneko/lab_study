package net.study.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import net.study.domain.enums.MessageStatus;
import net.study.domain.enums.StudyRequest;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/18/15 | 7:57 PM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob()
    @Column(nullable = false)
    private String message;

    @ManyToOne
    @JoinColumn(name="sender_id")
    @JsonBackReference
    private User sender;

    @ManyToOne
    @JoinColumn(name="receiver_id")
    @JsonBackReference
    private User receiver;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date sendDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date receiveDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MessageStatus messageStatus;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StudyRequest studyRequest;

    @OneToOne
    @JoinColumn(name="study_id")
    private Study study;

    public Message() {
    }

    public Message(String message, User sender, User receiver, Date sendDate, MessageStatus messageStatus, StudyRequest studyRequest, Study study) {
        this.message = message;
        this.sender = sender;
        this.receiver = receiver;
        this.sendDate = sendDate;
        this.messageStatus = messageStatus;
        this.studyRequest = studyRequest;
        this.study = study;
    }

    public Message(String message, User sender, User receiver, Date sendDate, MessageStatus messageStatus) {
        this.message = message;
        this.sender = sender;
        this.receiver = receiver;
        this.sendDate = sendDate;
        this.messageStatus = messageStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public MessageStatus getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(MessageStatus messageStatus) {
        this.messageStatus = messageStatus;
    }

    public StudyRequest getStudyRequest() {
        return studyRequest;
    }

    public void setStudyRequest(StudyRequest studyRequest) {
        this.studyRequest = studyRequest;
    }

    public Study getStudy() {
        return study;
    }

    public void setStudy(Study study) {
        this.study = study;
    }

    public String getReceiveDifferentTime(){
        if(this.receiveDate == null){
            return "Undetermined";
        }

        return getDifferentTime(this.receiveDate);
    }

    public String getSendDifferentTime(){
        return getDifferentTime(this.sendDate);
    }

    /*
    Login User Check
     */
    public boolean checkUser(User user){
        if(this.sender.getId() == user.getId() || this.receiver.getId() == user.getId()){
            return true;
        }
        return false;
    }

    /*
    Get Different Time
     */
    private String getDifferentTime(Date inputDate){
        long currentTime = System.currentTimeMillis();
        Date date = inputDate;
        long time = date.getTime();

        long differentTime = currentTime - time;

        if(differentTime < 60000){
            return "just now";
        }
        else if(differentTime < 3600000){
            return (int)(differentTime / 60000)+" minutes ago";
        }
        else if(differentTime < 86400000){
            return (int)(differentTime / 3600000)+" hours ago";
        }
        else {
            return new SimpleDateFormat("MM-dd").format(date).toString();
        }
    }
}
