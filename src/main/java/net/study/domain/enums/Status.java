package net.study.domain.enums;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/13/15 | 2:24 AM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

public enum Status {

    OPEN("모집"),
    CLOSE("종료");

    private String title;

    Status(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
