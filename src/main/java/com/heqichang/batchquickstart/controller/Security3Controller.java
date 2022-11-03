package com.heqichang.batchquickstart.controller;

import com.heqichang.batchquickstart.param.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/s3")
public class Security3Controller {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @RequestMapping("code")
    public Map<String, String> code() {

        String code = String.valueOf((int)(Math.random() * 1000000));
        redisTemplate.opsForValue().set("code", code, 5, TimeUnit.MINUTES);

        Map<String, String> result = new HashMap<>();

        result.put("code", code);
        return result;
    }

    @PostMapping("login")
    public String login(@RequestBody LoginParam loginParam) {

        System.out.println(loginParam);

        return "success";
    }
}
