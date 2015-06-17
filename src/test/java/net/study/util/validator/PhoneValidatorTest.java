package net.study.util.validator;

import net.study.Application;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/18/15 | 4:58 AM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class PhoneValidatorTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PhoneValidatorTest.class);

    @Autowired
    private PhoneValidator phoneValidator;

    private String[] validPhoneProvider;

    @Before
    public void initData(){
        validPhoneProvider = new String[] {
                "(0060)123-12345678", "(0060)12312345678", "(832)123-1234567", "(006)03-12345678",
                "(006)03-12345678", "00603-12345678", "0060312345678", "0000-123-12345678",
                "0000-12-12345678", "0000-1212345678", "1234-5678", "01-123-4567", "(0080) 123 12345678",
                "+82-123-1234567", "+82 123 1234567", "+800 01 12345678", "1-800-000-0000"
        };
    }

    @Test
    public void ValidPhoneTest() {

        for(String temp : validPhoneProvider){
            boolean valid = phoneValidator.validate(temp);
            LOGGER.debug("Phone is valid : {}, {}", temp, valid);
            //Assert.assertEquals(true, valid);
        }
    }
}
