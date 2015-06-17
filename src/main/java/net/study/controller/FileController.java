package net.study.controller;

import net.study.domain.Assets;
import net.study.domain.CurrentUser;
import net.study.repository.AssetsRepository;
import net.study.util.validator.ImageValidator;
import net.study.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/17/15 | 6:43 AM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

@Controller
@PropertySource("classpath:file.properties")
public class FileController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

    @Value("${file.identicon.filePath}")
    private String filePath;

    @Value("${file.identicon.realPath}")
    private String path;

    @Autowired
    private Utils utils;

    @Autowired
    private ImageValidator imageValidator;

    @Autowired
    private AssetsRepository assetsRepository;

    @RequestMapping(value="/upload", method= RequestMethod.GET)
    public String uploadForm() {
        return "common/upload";
    }

    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("file") MultipartFile file,
                                                 @ModelAttribute("currentUser")CurrentUser currentUser){

        LOGGER.debug("Upload file name={}, validated={}", file.getOriginalFilename(), imageValidator.validate(file.getOriginalFilename()));

        if (imageValidator.validate(file.getOriginalFilename())) {
            String realPath = path + utils.fileNameHelper();

            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath + realPath)));
                stream.write(bytes);
                stream.close();

                Assets assets = new Assets();
                assets.setFileName(file.getOriginalFilename());
                assets.setRealPath(realPath);
                assets.setUser(currentUser.getUser());
                assets.setFileSize(new Long(bytes.length));
                assets.setDownloadCount(0);

                assetsRepository.save(assets);

                return "You successfully uploaded " + file.getName() + "!";
            } catch (Exception e) {
                return "You failed to upload " + file.getName() + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload" + file.getName() + " because the file was not image.";
        }
    }
}
