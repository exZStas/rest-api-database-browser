package com.restdb.dbapi.config;

import com.restdb.dbapi.db_browser.dto.TableColumnsInfoDto;
import com.restdb.dbapi.db_browser.model.TableColumnsInfoView;
import com.restdb.dbapi.stored_db_connection.dto.StoredDatabaseConnectionDto;
import com.restdb.dbapi.stored_db_connection.model.StoredDatabaseConnection;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EntityToDtoMapper {

    StoredDatabaseConnectionDto storedDatabaseConnectionToDto(StoredDatabaseConnection storedDatabaseConnection);

    List<StoredDatabaseConnection> storedDatabaseConnectionsToDtos(List<StoredDatabaseConnection> storedDatabaseConnections);

    List<TableColumnsInfoDto> tableColumnsInfoViewsToDtos(List<TableColumnsInfoView> tableColumnsInfoViews);
}
