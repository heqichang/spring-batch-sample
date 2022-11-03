package com.heqichang.batchquickstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig3 {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf().disable()
                .headers().cacheControl().disable().and()
                .authorizeHttpRequests()
                .antMatchers("/s3/**").permitAll()
                .anyRequest().authenticated();

        return httpSecurity.build();

    }
}
