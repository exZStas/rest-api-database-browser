package com.restdb.dbapi.db_statistics;

import com.restdb.dbapi.db_statistics.dto.TableStatisticsView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("database_statistics")
@RestController
public class DatabaseStatisticsController {

    @Autowired
    private DatabaseStatisticsService databaseStatisticsService;

    @GetMapping("tables/{tableName}")
    public ResponseEntity<TableStatisticsView> getTableStatistics(@PathVariable(value = "tableName") String tableName) {
        TableStatisticsView tableStatistics = databaseStatisticsService.getTableStatistics(tableName);
        return new ResponseEntity<>(tableStatistics, HttpStatus.OK);
    }

    @GetMapping("tables")
    public ResponseEntity<Map<String,TableStatisticsView>> getDatabaseTablesStatistics() {
        Map<String,TableStatisticsView> tablesStatistics = databaseStatisticsService.getDatabaseTablesStatistics();
        return new ResponseEntity<>(tablesStatistics, HttpStatus.OK);
    }
}
