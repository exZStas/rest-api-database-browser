package com.restdb.dbapi;

import com.restdb.dbapi.model.StoredDatabaseConnection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import static java.util.Objects.nonNull;

@Service
@Slf4j
public class StoredDatabaseConnectionService {

    @Autowired
    private StoredDatabaseConnectionRepository storedDbRepository;

    //todo for security reasons better transfer passwords and other sensitive data in byte array.
    // using pw as string here for simplifying things
    public StoredDatabaseConnection createStoredDbConnection(String name, String hostName, Long port, String dbName,
                                                             String username, String password) {
        Assert.hasLength(name, "Custom db name can't be null or empty");
        Assert.hasLength(hostName, "Hostname name can't be null or empty");
        Assert.isTrue(port > 0, "Db port can't be null or less than zero");
        Assert.hasLength(dbName, "Db name can't be null or empty");
        Assert.hasLength(username, "Username can't be null or empty");
        Assert.hasLength(password, "Password can't be null or empty");

        StoredDatabaseConnection storedDbConnection = storedDbRepository.findStoredDatabaseConnectionByName(name);
        if(nonNull(storedDbConnection)) {
            log.warn(String.format("Database connection with name %s already exists, going to override previous connection!", name));
        } else storedDbConnection = new StoredDatabaseConnection();

        storedDbConnection.setName(name);
        storedDbConnection.setHostName(hostName);
        storedDbConnection.setPort(port);
        storedDbConnection.setDatabaseName(dbName);
        storedDbConnection.setUsername(username);
        storedDbConnection.setPassword(password);

        return storedDbRepository.save(storedDbConnection);
    }
}
