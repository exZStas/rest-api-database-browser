package com.restdb.dbapi.stored_db_connection;

import com.restdb.dbapi.stored_db_connection.model.StoredDatabaseConnection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoredDatabaseConnectionRepository extends JpaRepository<StoredDatabaseConnection, Long> {

    StoredDatabaseConnection findStoredDatabaseConnectionByName(String name);
}
