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

    FREE("무료"),
    CHARGED("유료");

    private String title;

    Charge(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
