package com.restdb.dbapi.stored_db_connection;

import com.restdb.dbapi.config.EntityToDtoMapper;
import com.restdb.dbapi.stored_db_connection.dto.StoredDatabaseConnectionDto;
import com.restdb.dbapi.stored_db_connection.model.StoredDatabaseConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("database_connections")
@RestController
public class StoredDatabaseConnectionController {

    @Autowired
    private StoredDatabaseConnectionService storedDbConnectionService;

    @Autowired
    private StoredDatabaseConnectionRepository storedDbConnectionRepository;

    @Autowired
    private EntityToDtoMapper storedDbConnectionMapper;

    @PostMapping
    public ResponseEntity<StoredDatabaseConnectionDto> addStoredDbConnection(@RequestBody StoredDatabaseConnectionDto storedDatabaseConnectionDto) {

        StoredDatabaseConnection storedDbConnection = storedDbConnectionService.createStoredDbConnection(storedDatabaseConnectionDto.getName(), storedDatabaseConnectionDto.getHostName(),
                storedDatabaseConnectionDto.getPort(), storedDatabaseConnectionDto.getDatabaseName(), storedDatabaseConnectionDto.getUsername(),
                storedDatabaseConnectionDto.getPassword());

        StoredDatabaseConnectionDto newStoredDatabaseConnectionDto = storedDbConnectionMapper.storedDatabaseConnectionToDto(storedDbConnection);
        return new ResponseEntity(newStoredDatabaseConnectionDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<StoredDatabaseConnectionDto>> getStoredDatabaseConnections() {
        List<StoredDatabaseConnection> storedDbConnections = storedDbConnectionRepository.findAll();
        List<StoredDatabaseConnection> storedDbConnectionsDto = storedDbConnectionMapper.storedDatabaseConnectionsToDtos(storedDbConnections);

        return new ResponseEntity(storedDbConnectionsDto, HttpStatus.OK);
    }

    @PostMapping("{storedDatabaseConnectionId}/connect")
    public void connectToStoredDbConnection(@PathVariable(value = "storedDatabaseConnectionId") Long storedDbConnectionId) {
        Optional<StoredDatabaseConnection> storedDbConnectionOptional = storedDbConnectionRepository.findById(storedDbConnectionId);
        if(storedDbConnectionOptional.isPresent()) {
            StoredDatabaseConnection storedDbConnection = storedDbConnectionOptional.get();
            storedDbConnectionService.connectToStoredDbConnection(storedDbConnection.getHostName(), storedDbConnection.getPort(),
                    storedDbConnection.getDatabaseName(), storedDbConnection.getUsername(), storedDbConnection.getPassword());
        }
    }

}
