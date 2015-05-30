package net.study.config;

import net.study.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 5/30/15 | 6:28 PM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

@Configuration
public class OAuth2ServerConfig {

    private static final String RESOURCE_ID = "restservice";

    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends
            ResourceServerConfigurerAdapter {

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            // @formatter:off
            resources
                    .resourceId(RESOURCE_ID);
            // @formatter:on
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            // @formatter:off
            http
                    .authorizeRequests()
                        .antMatchers("/", "/article/list", "/article/read/*", "/user/*").permitAll()
                        .antMatchers("/admin/**").hasRole("ADMIN")
                        .antMatchers("/article/write").hasRole("USER")
                        .anyRequest().authenticated()
                        .and()
                    .formLogin()
                        .loginPage("/login").failureUrl("/login?error").defaultSuccessUrl("/")
                        .permitAll()
                        .and()
                    .logout()
                        .permitAll();
            // @formatter:on
        }
    }

//    @Configuration
//    @EnableAuthorizationServer
//    protected static class AuthorizationServerConfiguration extends
//            AuthorizationServerConfigurerAdapter {
//
//        private TokenStore tokenStore = new InMemoryTokenStore();
//
//        @Autowired
//        @Qualifier("authenticationManagerBean")
//        private AuthenticationManager authenticationManager;
//
//        @Autowired
//        private CustomUserDetailsService userDetailsService;
//
//        @Override
//        public void configure(AuthorizationServerEndpointsConfigurer endpoints)
//                throws Exception {
//            // @formatter:off
//            endpoints
//                    .tokenStore(this.tokenStore)
//                    .authenticationManager(this.authenticationManager)
//                    .userDetailsService(userDetailsService);
//            // @formatter:on
//        }
//
//        @Override
//        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//            // @formatter:off
//            clients
//                    .inMemory()
//                    .withClient("clientapp")
//                    .authorizedGrantTypes("password", "refresh_token")
//                    .authorities("USER")
//                    .scopes("read", "write")
//                    .resourceIds(RESOURCE_ID)
//                    .secret("123456");
//            // @formatter:on
//        }
//
//        @Bean
//        @Primary
//        public DefaultTokenServices tokenServices() {
//            DefaultTokenServices tokenServices = new DefaultTokenServices();
//            tokenServices.setSupportRefreshToken(true);
//            tokenServices.setTokenStore(this.tokenStore);
//            return tokenServices;
//        }
//    }

}