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

    STUDY("스터디"),
    LECTURE("강의");

    private String title;

    Way(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
