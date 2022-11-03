package com.heqichang.batchquickstart.entity;

import lombok.Data;

import java.util.List;

@Data
public class Admin {

    private Long id;

    private String name;

    private String mobile;

    private String pass;

    private List<Role> roles;
}
