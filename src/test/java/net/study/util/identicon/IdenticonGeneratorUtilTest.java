package net.study.util.identicon;

import net.study.Application;
import net.study.util.Utils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/17/15 | 1:18 AM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class IdenticonGeneratorUtilTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(IdenticonGeneratorUtilTest.class);

    private final String emails[] = {
            "a@a.com",
            "b@b.com",
            "c@c.com",
            "d@d.com"
    };

    @Autowired
    IdenticonGeneratorUtil identiconGeneratorUtil;

    @Autowired
    Utils utils;

    @Test
    public void generate() throws Exception {
        Map<String, String> map;

        for(String email : emails){
            map = identiconGeneratorUtil.generator(email);
            LOGGER.debug("Generate Identicon, realPath={}, fileName={}", map.get("realPath"), map.get("fileName"));

            utils.fileRemoveHelper(map.get("realPath"));
        }
    }
}
