package net.study.domain.enums;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/5/15 | 3:13 PM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

public enum Charge {

    FREE("무료","info"),
    CHARGED("유료","warning");

    private String title;
    private String color;

    Charge(String title, String color) {
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
