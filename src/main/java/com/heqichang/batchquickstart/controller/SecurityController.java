package com.heqichang.batchquickstart.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/s")
public class SecurityController {

    @RequestMapping("add")
    public String add() {

        return "success";
    }
}
