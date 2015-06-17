package net.study.util;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/17/15 | 6:21 AM
 * Description  : http://www.mkyong.com/regular-expressions/how-to-validate-image-file-extension-with-regular-expression/
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

@Component
public class ImageValidator {
    private Pattern pattern;
    private Matcher matcher;

    private static final String IMAGE_PATTERN = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)";

    public ImageValidator(){
        pattern = Pattern.compile(IMAGE_PATTERN);
    }

    /**
     * Validate image with regular expression
     * @param image image for validation
     * @return true valid image, false invalid image
     */
    public boolean validate(final String image){

        matcher = pattern.matcher(image);
        return matcher.matches();

    }
}
