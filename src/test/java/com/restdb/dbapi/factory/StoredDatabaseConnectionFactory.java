package com.restdb.dbapi.factory;

import com.restdb.dbapi.stored_db_connection.StoredDatabaseConnectionRepository;
import com.restdb.dbapi.stored_db_connection.model.StoredDatabaseConnection;
import org.springframework.beans.factory.annotation.Autowired;

public class StoredDatabaseConnectionFactory {

    @Autowired
    private StoredDatabaseConnectionRepository storedDbConnectionRepository;

    public StoredDatabaseConnection createStoredDbConnection(String name, String hostName, Long port, String dbName,
                                                             String userName, String password) {
        StoredDatabaseConnection storedDatabaseConnection = new StoredDatabaseConnection();
        storedDatabaseConnection.setName(name);
        storedDatabaseConnection.setHostName(hostName);
        storedDatabaseConnection.setPort(port);
        storedDatabaseConnection.setDatabaseName(dbName);
        storedDatabaseConnection.setUsername(userName);
        storedDatabaseConnection.setPassword(password);

        return storedDbConnectionRepository.saveAndFlush(storedDatabaseConnection);
    }
}
