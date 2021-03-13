package com.restdb.dbapi.dto;

import lombok.Builder;
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
