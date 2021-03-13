package com.restdb.dbapi.stored_db_connection.dto;

import lombok.Data;

@Data
public class StoredDatabaseConnectionDto {

    Long id;
    String name;
    String hostName;
    Long port;
    String databaseName;
    String username;
    String password;
}
