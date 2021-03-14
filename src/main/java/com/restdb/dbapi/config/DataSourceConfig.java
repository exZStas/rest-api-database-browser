package com.restdb.dbapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Value("${default-jdbc-url}")
    private String defaultJdbcUrl;

    @Bean(name = "dsCustom")
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .username("sa")
                .password("password")
                .url(defaultJdbcUrl)
                .driverClassName("org.h2.Driver")
                .build();
    }

    @Bean(name = "jdbcCustom")
    @Autowired
    public JdbcTemplate jdbcTemplate(@Qualifier("dsCustom") DataSource dsCustom) {
        return new JdbcTemplate(dsCustom);
    }
}
