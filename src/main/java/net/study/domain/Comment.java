package net.study.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 5/27/15 | 9:43 AM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

@Entity
public class Comment extends Reply{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long groupId;

    @Column(nullable = false, length = 12)
    private String sequenceNumber;

    @Lob()
    @Column(nullable = false)
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date postingDate;

    @ManyToOne
    @JoinColumn(name="board_id")
    @JsonBackReference
    private Board board;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Comment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(String sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(Date postingDate) {
        this.postingDate = postingDate;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /*
    Login User Check
     */
    public boolean checkUser(User user){
        if(this.user.getId() == user.getId()){
            return true;
        }
        return false;
    }

    /*
    Get Different Time
     */
    public String getDifferentTime(){
        long currentTime = System.currentTimeMillis();
        Date date = this.postingDate;
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

    /*
    Get reply level
     */
    public int getLevel(){
        if(sequenceNumber == null)          return -1;
        if(sequenceNumber.length() != 12)   return -1;

        if(sequenceNumber.endsWith("99"))   return 0;   // root
        return 1;   // fist children
    }
}
