package net.study.controller;

import net.study.domain.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 5/31/15 | 1:51 PM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

@Controller
public class HomeController{

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping("/")
    public String getHomePage(@ModelAttribute("currentUser")CurrentUser currentUser) {
        LOGGER.debug("Getting home page");

        if(currentUser==null){
            return "index";
        } else {
            return "home";
        }
    }

    @RequestMapping(value = "/400")
    public String error400() {
        LOGGER.debug("Getting error page");
        return "error/400";
    }

    @RequestMapping(value = "/403")
    public String error403() {
        LOGGER.debug("Getting error page");
        return "error/403";
    }

    @RequestMapping(value = "/404")
    public String error404() {
        LOGGER.debug("Getting error page");
        return "error/404";
    }

    @RequestMapping(value = "/500")
    public String error500() {
        LOGGER.debug("Getting error page");
        return "error/500";
    }
}

