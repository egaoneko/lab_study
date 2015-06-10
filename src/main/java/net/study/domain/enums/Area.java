package net.study.domain.enums;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/5/15 | 2:31 AM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

public enum Area {

    SEOUL("서울"),
    INCHEON("인천"),
    SUWON("수원"),
    UIJEONGBU("의정부"),
    BUSAN("부산"),
    GWANGJU("광주"),
    DAEGU("대전"),
    DAEJEON("대구"),
    WONJU("원주"),
    GANGNEUNG("강릉"),
    JEONJU("전주"),
    NAJU("나주"),
    GYEONGJU("경주"),
    SANGJU("상주"),
    CHUNGJU("충주"),
    CHEONGJU("청주"),
    JEJU("제주"),
    ETC("기타");

    private String title;

    Area(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
