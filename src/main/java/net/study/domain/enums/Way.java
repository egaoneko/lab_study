package net.study.domain.enums;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/5/15 | 3:52 PM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

public enum Way {

    STUDY("스터디","info"),
    LECTURE("강의","warning");

    private String title;
    private String color;

    Way(String title, String color) {

        this.title = title;
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public String getColor() {
        return color;
    }
}
