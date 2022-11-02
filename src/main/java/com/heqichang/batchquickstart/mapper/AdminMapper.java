package com.heqichang.batchquickstart.mapper;

import com.heqichang.batchquickstart.entity.Admin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

// 加了 mapper 注解，就不用使用 mapperScan 来扫描了
@Mapper
public interface AdminMapper {

    @Select("select * from admin")
    List<Admin> getAll();

    @Select("select * from admin where name = #{name}")
    Admin getByName(String name);

    @Insert("insert into admin(name, mobile) values (#{name}, #{mobile})")
    int insert(String name, String mobile);
}
