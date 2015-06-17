package net.study.util;

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
 * Date         : 6/17/15 | 6:23 AM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ImageValidatorTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageValidatorTest.class);

    @Autowired
    private ImageValidator imageValidator;

    private String[] validImageProvider1;
    private String[] validImageProvider2;

    @Before
    public void initData(){
        imageValidator = new ImageValidator();
        validImageProvider1 = new String[] {
                "a.jpg", "a.gif","a.png", "a.bmp",
                "..jpg", "..gif","..png", "..bmp",
                "a.JPG", "a.GIF","a.PNG", "a.BMP",
                "a.JpG", "a.GiF","a.PnG", "a.BmP",
                "jpg.jpg", "gif.gif","png.png", "bmp.bmp"
        };
        validImageProvider2 = new String[] {
                "a.jpg", "a.gif","a.png", "a.bmp",
                "..jpg", "..gif","..png", "..bmp",
                "a.JPG", "a.GIF","a.PNG", "a.BMP",
                "a.JpG", "a.GiF","a.PnG", "a.BmP",
                "jpg.jpg", "gif.gif","png.png", "bmp.bmp"
        };
    }

    @Test
    public void ValidImageTest() {

        for(String temp : validImageProvider1){
            boolean valid = imageValidator.validate(temp);
            LOGGER.debug("Image is valid : {}, {}", temp, valid);
            //Assert.assertEquals(true, valid);
        }

    }

    @Test
    public void InValidImageTest() {

        for(String temp : validImageProvider2){
            boolean valid = imageValidator.validate(temp);
            LOGGER.debug("Image is valid : {}, {}", temp, valid);
            //Assert.assertEquals(false, valid);
        }
    }
}
