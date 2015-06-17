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
 * Date         : 6/18/15 | 4:29 AM
 * Description  : Email Validator(http://www.mkyong.com/regular-expressions/how-to-validate-email-address-with-regular-expression/)
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class EmailValidatorTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailValidatorTest.class);

    @Autowired
    private EmailValidator emailValidator;

    private String[] validImageProvider1;
    private String[] validImageProvider2;

    @Before
    public void initData() {
        validImageProvider1 = new String[] { "mkyong@yahoo.com",
                "mkyong-100@yahoo.com", "mkyong.100@yahoo.com",
                "mkyong111@mkyong.com", "mkyong-100@mkyong.net",
                "mkyong.100@mkyong.com.au", "mkyong@1.com",
                "mkyong@gmail.com.com", "mkyong+100@gmail.com",
                "mkyong-100@yahoo-test.com" };
        validImageProvider2 = new String[] { "mkyong", "mkyong@.com.my",
                "mkyong123@gmail.a", "mkyong123@.com", "mkyong123@.com.com",
                ".mkyong@mkyong.com", "mkyong()*@gmail.com", "mkyong@%*.com",
                "mkyong..2002@gmail.com", "mkyong.@gmail.com",
                "mkyong@mkyong@gmail.com", "mkyong@gmail.com.1a" };
    }

    @Test
    public void ValidEmailTest() {

        for (String temp : validImageProvider1) {
            boolean valid = emailValidator.validate(temp);
            LOGGER.debug("Email is valid : {}, {}", temp, valid);
            Assert.assertEquals(valid, true);
        }

        for (String temp : validImageProvider2) {
            boolean valid = emailValidator.validate(temp);
            LOGGER.debug("Email is valid : {}, {}", temp, valid);
            Assert.assertEquals(valid, false);
        }

    }
}