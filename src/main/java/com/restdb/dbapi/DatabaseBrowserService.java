package com.restdb.dbapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseBrowserService {

    @Autowired
    private CustomDatabaseBrowserRepository databaseBrowserRepository;

    public List<String> getTables() {
        return databaseBrowserRepository.getTables();
    }
}
