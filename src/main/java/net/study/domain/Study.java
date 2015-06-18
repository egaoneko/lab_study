package net.study.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import net.study.domain.enums.*;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/5/15 | 9:20 PM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

@Entity
public class Study {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob()
    @Column(nullable = false)
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date postingDate;

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonBackReference
    private User user;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Area area;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Charge charge;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OnOffLine onOffLine;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Way way;

    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer price;

    @Column(nullable = false, columnDefinition = "int default 1")
    private Integer participant;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToMany
    @JoinTable(name = "STUDY_BOOK",
            joinColumns = @JoinColumn(name = "STUDY_ID_FRK"),
            inverseJoinColumns = @JoinColumn(name = "BOOK_ID_FRK"))
    private Set<Book> bookSet;

    @OneToMany(
            targetEntity = Board.class,
            mappedBy = "study",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.EAGER
    )
    @JsonBackReference
    private List<Board> boardList;

    @ManyToMany
    @JoinTable(name = "PARTICIPANTS",
            joinColumns = @JoinColumn(name = "STUDY_ID_FRK"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID_FRK"))
    @JsonBackReference
    private Set<User> participants;

    public Study() {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Charge getCharge() {
        return charge;
    }

    public void setCharge(Charge charge) {
        this.charge = charge;
    }

    public OnOffLine getOnOffLine() {
        return onOffLine;
    }

    public void setOnOffLine(OnOffLine onOffLine) {
        this.onOffLine = onOffLine;
    }

    public Way getWay() {
        return way;
    }

    public void setWay(Way way) {
        this.way = way;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getParticipant() {
        return participant;
    }

    public void setParticipant(Integer participant) {
        this.participant = participant;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<Book> getBookSet() {
        return bookSet;
    }

    public void setBookSet(Set<Book> bookSet) {
        this.bookSet = bookSet;
    }

    public List<Board> getBoardList() {
        return boardList;
    }

    public void setBoardList(List<Board> boardList) {
        this.boardList = boardList;
    }

    public Set<User> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<User> participants) {
        this.participants = participants;
    }

    public int getParticipantNum(){
        return participants.size();
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
