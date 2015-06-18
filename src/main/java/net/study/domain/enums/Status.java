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

    OPEN("모집","info"),
    EXCESS("초과","warning"),
    CLOSE("종료","danger");

    private String title;
    private String color;

    Status(String title, String color) {
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
