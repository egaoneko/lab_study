package net.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;

import java.util.Properties;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/15/15 | 1:13 AM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

public class VelocityConfig {

    @Bean
    public VelocityConfigurer velocityConfigurer() {
        VelocityConfigurer configurer = new VelocityConfigurer();
        Properties properties = new Properties();
        properties.setProperty("input.encoding", "UTF-8");
        properties.setProperty("output.encoding", "UTF-8");
        configurer.setVelocityProperties(properties);
        return configurer;
    }
}
