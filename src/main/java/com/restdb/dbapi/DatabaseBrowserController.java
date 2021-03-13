package com.restdb.dbapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("database_browser")
@RestController
public class DatabaseBrowserController {

    @Autowired
    private DatabaseBrowserService databaseBrowserService;

    @GetMapping("/tables")
    public ResponseEntity<List<String>> getAllTables() {
        return new ResponseEntity<>(databaseBrowserService.getTables(), HttpStatus.OK);
    }
}
