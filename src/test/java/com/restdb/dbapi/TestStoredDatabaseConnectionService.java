package com.restdb.dbapi;

import com.restdb.dbapi.factory.StoredDatabaseConnectionFactory;
import com.restdb.dbapi.stored_db_connection.StoredDatabaseConnectionService;
import com.restdb.dbapi.stored_db_connection.model.StoredDatabaseConnection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static java.util.Objects.nonNull;

@SpringBootTest
class TestStoredDatabaseConnectionService {

    @Autowired
    private StoredDatabaseConnectionFactory storedDbConnectionFactory;

    @Autowired
    private StoredDatabaseConnectionService storedDbConnectionService;

    private StoredDatabaseConnection storedDbConn_1;

    @BeforeEach
    public void setUp() {
        storedDbConn_1 = storedDbConnectionFactory.createStoredDbConnection("My db", "localhost", 1521l,
                "My db", "superuser", "123");
    }

    @Test
    void test_createStoredDatabaseConnection_Successful() {
        //given
        //when
        StoredDatabaseConnection actual = storedDbConnectionService.createStoredDbConnection("My db 1",
                "localhost", 1521l ,"mydb", "un", "11");
        //then
        Assertions.assertTrue(nonNull(actual));
    }

}
