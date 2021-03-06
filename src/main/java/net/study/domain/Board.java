package net.study.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 5/26/15 | 7:59 PM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Lob()
    @Column(nullable = false)
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date postingDate;

    @Column(nullable = false)
    private Integer readCount;

    @OneToMany(
            targetEntity = Comment.class,
            mappedBy = "board",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.EAGER
    )
    private List<Comment> commentList;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="study_id")
    private Study study;

    public Board() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Study getStudy() {
        return study;
    }

    public void setStudy(Study study) {
        this.study = study;
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
}
