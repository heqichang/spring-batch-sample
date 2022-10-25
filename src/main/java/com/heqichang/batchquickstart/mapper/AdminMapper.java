package com.heqichang.batchquickstart.mapper;

import com.heqichang.batchquickstart.entity.Admin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminMapper {

    @Select("select * from admin")
    List<Admin> getAll();

    @Insert("insert into admin(name, mobile) values (#{name}, #{mobile})")
    int insert(String name, String mobile);
}
