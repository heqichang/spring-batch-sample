package com.heqichang.batchquickstart.rowmapper;

import com.heqichang.batchquickstart.entity.Admin;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRowMapper implements RowMapper<Admin> {

    @Override
    public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {

        Admin admin = new Admin();

        admin.setId(rs.getLong("id"));
        admin.setName(rs.getString("name"));
        admin.setMobile(rs.getString("mobile"));

        return admin;
    }
}
