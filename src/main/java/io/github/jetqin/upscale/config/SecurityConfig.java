package io.github.jetqin.upscale.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Slf4j
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // @formatter:off
    protected void configure(HttpSecurity http) throws Exception {
        log.info("{} using Custome configure(HttpSecurity).","Upscale");
        http.httpBasic().disable();
    }
    // @formatter:on
}
