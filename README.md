# rest-api-database-browser

Current solution allows to store multiple database connections and connect to them in order to browse some data or get statistics.
Works with oracle database only.

There are several endpoints worth mentioning:
1) Save database connection
```
    POST localhost:8080/database_connections
    Example post body:
    {
      "name": "his test02",                               // custom connection name
      "hostName": "prm-lsf-ota-ora-03.idm.promedico.nl",  // db host name
      "port": 1521,                                       // db port
      "databaseName": "PROMDEV1",                         // SID
      "username": "tst02_his",                            // username of schema you want to connect to
      "password": "his"                                   // schema password
    }
```
    
2) Get list of stored database connections 
``` 
   GET localhost:8080/database_connections
```
   
3) Connect to stored database connection by id
``` 
   POST localhost:8080/database_connections/{databaseConnectionId}/connect
```

4) List tables of database you are connected to
```
   GET localhost:8080/database_browser/tables
```

5) List schemas of database you are connected to
```
   GET localhost:8080/database_browser/schemas
```   
6) List columns in a table
```
   GET localhost:8080/database_browser/tables/{tableName}
```

7) Table data preview
```
   GET localhost:8080/database_browser/tables/{tableName}/preview
```

8) Statistics regarding single table
```
   GET localhost:8080/database_statistics/tables/{tableName}
```

9) Statistics regarding all tables in database
```
   GET localhost:8080/database_statistics/tables
```
