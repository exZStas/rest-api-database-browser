package com.restdb.dbapi;

import com.restdb.dbapi.exception.StoredDatabaseConnectionException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;

@Aspect
@Configuration
public class CheckDataSourceReadyAspect {

    @Value("${default-jdbc-url}")
    private String defaultJdbcUrl;

    @Autowired
    @Qualifier("jdbcCustom")
    private JdbcTemplate jdbcTemplate;

    @Before("execution(* com.restdb.dbapi.db_browser.DatabaseBrowserController.*(..))")
    public void before(JoinPoint joinPoint) throws SQLException, StoredDatabaseConnectionException {
        String jdbcUrl = jdbcTemplate.getDataSource().getConnection().getMetaData().getURL();

        //if try to browse default db throw exception since users not allowed to do this
        if(defaultJdbcUrl.contains(jdbcUrl)) {
            throw new StoredDatabaseConnectionException("Stored database connection is not established. Not allowed to browse database!");
        }
    }
}
