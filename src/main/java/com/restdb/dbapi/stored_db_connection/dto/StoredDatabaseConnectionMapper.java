package com.restdb.dbapi.stored_db_connection.dto;

import com.restdb.dbapi.stored_db_connection.model.StoredDatabaseConnection;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StoredDatabaseConnectionMapper {

    StoredDatabaseConnectionDto storedDatabaseConnectionToDto(StoredDatabaseConnection storedDatabaseConnection);

    List<StoredDatabaseConnection> storedDatabaseConnectionsToDtos(List<StoredDatabaseConnection> storedDatabaseConnections);
}
