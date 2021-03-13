package com.restdb.dbapi.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FactoryConfiguration {

    @Bean
    public StoredDatabaseConnectionFactory getStoredDatabaseConnectionFactory() {
        return new StoredDatabaseConnectionFactory();
    }
}
