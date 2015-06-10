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

    OFFLINE("오프라인"),
    ONLINE("온라인");

    private String title;

    OnOffLine(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
