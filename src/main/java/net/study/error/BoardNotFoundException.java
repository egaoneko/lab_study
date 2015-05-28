package net.study.error;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 5/29/15 | 2:00 AM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

public class BoardNotFoundException extends NotFoundException{
    public BoardNotFoundException(String msg){
        super(msg);
    }
}
