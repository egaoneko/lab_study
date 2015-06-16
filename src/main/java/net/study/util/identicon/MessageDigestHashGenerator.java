package net.study.util.identicon;

import java.security.MessageDigest;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/17/15 | 1:06 AM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

public class MessageDigestHashGenerator implements HashGeneratorInterface {
    MessageDigest messageDigest;

    public MessageDigestHashGenerator(String algorithim) {
        try {
            messageDigest = MessageDigest.getInstance(algorithim);
        }catch(Exception e) {
            System.err.println("Error setting algorithim: " + algorithim);
        }
    }

    public byte[] generate(String input) {
        return messageDigest.digest(input.getBytes());
    }
}