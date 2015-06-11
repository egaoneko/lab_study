package net.study.domain.form;

import net.study.domain.enums.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/10/15 | 12:36 PM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

public class StudyCreateForm {

    private Long id;

    @NotEmpty
    private String title = "";

    @NotEmpty
    private String content = "";

    @NotNull
    private Long userId;

    @NotNull
    private Category category = Category.ETC;

    @NotNull
    private Area area = Area.ETC;

    @NotNull
    private Charge charge = Charge.FREE;

    @NotNull
    private OnOffLine onOffLine = OnOffLine.OFFLINE;

    @NotNull
    private Way way = Way.STUDY;

    @NotNull
    private int price = 0;

    @NotNull
    private int participant = 1;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getParticipant() {
        return participant;
    }

    public void setParticipant(int participant) {
        this.participant = participant;
    }

    public Category[] getCategoryList(){
        return Category.values();
    }

    public Area[] getAreaList(){
        return Area.values();
    }

    public Charge[] getChargeList(){
        return Charge.values();
    }

    public OnOffLine[] getOnOffLineList(){
        return OnOffLine.values();
    }

    public Way[] getWayList(){
        return Way.values();
    }

    public String toString() {
        return "StudyCreateForm{" +
                "title=" + title + '\'' +
                "content=" + content + '\'' +
                "userId=" + userId + '\'' +
                "category=" + category + '\'' +
                "area=" + area + '\'' +
                "charge=" + charge + '\'' +
                "onOffLine=" + onOffLine + '\'' +
                "way=" + way + '\'' +
                "price=" + price + '\'' +
                "participant" + participant +
                '}';
    }
}
