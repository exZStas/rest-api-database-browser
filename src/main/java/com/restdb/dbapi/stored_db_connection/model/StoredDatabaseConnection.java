package com.restdb.dbapi.stored_db_connection.model;

import lombok.Data;

import javax.persistence.*;

import static com.restdb.dbapi.DatabaseConstants.DB_API_SEQUENCE;

@Entity
@Data
@Table(name = "STORED_DATABASE_CONNECTION")
public class StoredDatabaseConnection {

    public static final int VARCHAR_FIELD_MAX_LENGTH = 50;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = DB_API_SEQUENCE)
    @SequenceGenerator(allocationSize = 1, name = DB_API_SEQUENCE, sequenceName = DB_API_SEQUENCE)
    @Column(name = "ID", unique = true, nullable = false, precision = 19)
    private Long id;

    @Column(name = "name", nullable = false, length = VARCHAR_FIELD_MAX_LENGTH)
    private String name;

    @Column(name = "hostname", nullable = false, length = VARCHAR_FIELD_MAX_LENGTH)
    private String hostName;

    @Column(name = "port", nullable = false)
    private Long port;

    @Column(name = "database_name", nullable = false, length = VARCHAR_FIELD_MAX_LENGTH)
    private String databaseName;

    @Column(name = "username", nullable = false, length = VARCHAR_FIELD_MAX_LENGTH)
    private String username;

    @Column(name = "password", nullable = false, length = VARCHAR_FIELD_MAX_LENGTH)
    private String password;
}
