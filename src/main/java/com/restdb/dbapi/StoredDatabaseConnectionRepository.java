package com.restdb.dbapi;

import com.restdb.dbapi.model.StoredDatabaseConnection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoredDatabaseConnectionRepository extends JpaRepository<StoredDatabaseConnection, Long> {

    StoredDatabaseConnection findStoredDatabaseConnectionByName(String name);
}
