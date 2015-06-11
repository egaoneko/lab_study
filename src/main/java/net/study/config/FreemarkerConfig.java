package net.study.config;


import kr.pe.kwonnam.freemarker.inheritance.BlockDirective;
import kr.pe.kwonnam.freemarker.inheritance.ExtendsDirective;
import kr.pe.kwonnam.freemarker.inheritance.PutDirective;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.HashMap;
import java.util.Map;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/11/15 | 5:53 PM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

@Configuration
public class FreemarkerConfig {

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer fmc = new FreeMarkerConfigurer();

        Map<String, Object> freemarkerLayoutDirectives = new HashMap<>();
        freemarkerLayoutDirectives.put("extends", new ExtendsDirective());
        freemarkerLayoutDirectives.put("block", new BlockDirective());
        freemarkerLayoutDirectives.put("put", new PutDirective());

        Map<String, Object> layout = new HashMap<>();
        layout.put("layout", freemarkerLayoutDirectives);

        fmc.setFreemarkerVariables(layout);
        fmc.setDefaultEncoding("UTF-8");
        fmc.setTemplateLoaderPath("classpath:templates");
        return fmc;
    }

    @Bean
    public ViewResolver freeMarkerViewResolver() {
        FreeMarkerViewResolver fvr = new FreeMarkerViewResolver();

        fvr.setCache(true);
        fvr.setPrefix("");
        fvr.setSuffix(".ftl");
        fvr.setContentType("text/html;charset=UTF-8");
        fvr.setExposeSpringMacroHelpers(true);
        fvr.setExposeRequestAttributes(true);
        fvr.setExposeSessionAttributes(true);
        fvr.setRequestContextAttribute("rc");
        return fvr;
    }

}
