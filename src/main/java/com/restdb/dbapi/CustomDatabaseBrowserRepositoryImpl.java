package com.restdb.dbapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomDatabaseBrowserRepositoryImpl implements CustomDatabaseBrowserRepository{

    @Autowired
    @Qualifier("jdbcCustom")
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<String> getTables() {
        String sql = "SELECT DISTINCT OBJECT_NAME FROM USER_OBJECTS WHERE OBJECT_TYPE = 'TABLE'";

        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString(1));
    }
}
