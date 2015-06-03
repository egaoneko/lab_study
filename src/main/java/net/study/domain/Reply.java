package net.study.domain;

import javax.persistence.Column;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/3/15 | 12:13 AM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

public abstract class Reply {

    public String sequenceNumber;

    public abstract Long getId();
    public abstract String getSequenceNumber();
    public abstract void setSequenceNumber(String sequenceNumber);
    public abstract int getLevel();

    /*
    Get reply level

    public int getLevel(){
        if(sequenceNumber == null)          return -1;
        if(sequenceNumber.length() != 12)   return -1;

        if(sequenceNumber.endsWith("99"))   return 0;   // root
        return 1;   // fist children
    }
    */

    /*
    Get reply level (Level 3)

    public int getLevel(){
        if(sequenceNumber == null)          return -1;
        if(sequenceNumber.length() != 16)   return -1;

        if(sequenceNumber.endsWith("999999"))   return 0;   // root
        if(sequenceNumber.endsWith("9999"))     return 1;   // fist children
        if(sequenceNumber.endsWith("99"))       return 2;   // second children
        return 3;                                           // third children
    }
    */
}
