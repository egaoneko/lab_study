package net.study.util.validator;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/18/15 | 4:53 AM
 * Description  : Phone Validator(http://stackoverflow.com/questions/2555182/java-phone-number-validation)
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

@Component
public class PhoneValidator {
    private Pattern pattern;
    private Matcher matcher;

    private static final String PHONE_PATTERN = "^(([(]?(\\d{2,4})[)]?)|(\\d{2,4})|([+1-9]+\\d{1,2}))?[-\\s]?(\\d{2,3})?[-\\s]?((\\d{7,8})|(\\d{3,4}[-\\s]\\d{3,4}))$";

    public PhoneValidator(){
        pattern = Pattern.compile(PHONE_PATTERN);
    }

    /**
     * Validate phone with regular expression
     * @param phone phone for validation
     * @return true valid phone, false invalid phone
     */
    public boolean validate(final String phone){

        matcher = pattern.matcher(phone);
        return matcher.matches();

    }
}
