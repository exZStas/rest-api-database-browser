package com.restdb.dbapi;

import com.restdb.dbapi.dto.StoredDatabaseConnectionDto;
import com.restdb.dbapi.dto.StoredDatabaseConnectionMapper;
import com.restdb.dbapi.model.StoredDatabaseConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("database_connections")
@RestController
public class StoredDatabaseConnectionController {

    @Autowired
    private StoredDatabaseConnectionService storedDbConnectionService;

    @Autowired
    private StoredDatabaseConnectionRepository storedDbConnectionRepository;

    @Autowired
    private StoredDatabaseConnectionMapper storedDbConnectionMapper;

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

}
