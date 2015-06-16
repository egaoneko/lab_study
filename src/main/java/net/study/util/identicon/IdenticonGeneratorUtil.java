package net.study.util.identicon;

import net.study.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/17/15 | 1:11 AM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

@Service
@PropertySource("classpath:file.properties")
public class IdenticonGeneratorUtil {

    @Autowired
    Utils utils;

    @Value("${file.identicon.filePath}")
    private String filePath;

    @Value("${file.identicon.realPath}")
    private String path;

    public Map<String, String> generator(String email) throws IOException {

        Map<String, String> map = new HashMap<>();

        String realPath = path + utils.fileNameHelper();
        String fileName = email + ".png";

        HashGeneratorInterface hashGenerator = new MessageDigestHashGenerator("MD5");

        BufferedImage identicon = IdenticonGenerator.generate(email, hashGenerator);

        //save identicon to file
        ImageIO.write(identicon, "PNG", new File(filePath + realPath));


        map.put("realPath", realPath);
        map.put("fileName", fileName);
        return map;
    }
}
