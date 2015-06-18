package net.study.domain.enums;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/5/15 | 3:19 PM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

public enum OnOffLine {

    OFFLINE("오프라인","info"),
    ONLINE("온라인","warning");

    private String title;
    private String color;

    OnOffLine(String title, String color) {
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
