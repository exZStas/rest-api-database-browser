package com.restdb.dbapi.dto;

import com.restdb.dbapi.model.StoredDatabaseConnection;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StoredDatabaseConnectionMapper {

    StoredDatabaseConnectionDto storedDatabaseConnectionToDto(StoredDatabaseConnection storedDatabaseConnection);

    List<StoredDatabaseConnection> storedDatabaseConnectionsToDtos(List<StoredDatabaseConnection> storedDatabaseConnections);
}
