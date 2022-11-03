package com.heqichang.batchquickstart.mapper;

import com.heqichang.batchquickstart.entity.Admin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper2 {

    Admin getOneByName(String name);
}
