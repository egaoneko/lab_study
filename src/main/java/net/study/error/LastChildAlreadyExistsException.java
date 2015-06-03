package net.study.error;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/3/15 | 12:09 AM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

public class LastChildAlreadyExistsException extends Exception {
    public LastChildAlreadyExistsException(String msg){
        super(msg);
    }
}
