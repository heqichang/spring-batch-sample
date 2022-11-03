package com.heqichang.batchquickstart.controller;

import com.heqichang.batchquickstart.entity.Admin;
import com.heqichang.batchquickstart.mapper.AdminMapper2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/m")
public class MyBatisController {

    @Autowired
    private AdminMapper2 adminMapper2;


    @RequestMapping("get")
    public  Admin get() {
        // 测试一对多关联
        Admin admin = adminMapper2.getOneByName("nio");
        return admin;
    }


}
