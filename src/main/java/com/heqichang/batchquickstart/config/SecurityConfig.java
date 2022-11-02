package com.heqichang.batchquickstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// 过期问题
// https://www.cnblogs.com/cnblog-user/p/16375282.html
//@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode("123");

        // 没有 roles 会报错 Error creating bean with name 'springSecurityFilterChain' defined in class path
        // 没有 passwordEncoder 用明文密码也无法登录成功
        auth.inMemoryAuthentication().passwordEncoder(encoder).withUser("admin").password(password).roles("admin");
        // 下面是有了 passwordEncoder 的 bean 后，可以省略 passwordEncoder 方法
//        auth.inMemoryAuthentication().withUser("admin").password(password).roles("admin");
    }

    // 可以在这里创建 bean，也可以在 configure 里使用 passwordEncoder 方法
//    @Bean
//    BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}
