package com.restdb.dbapi.db_statistics;

import com.restdb.dbapi.db_statistics.dto.TableStatisticsView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Map;

@Service
public class DatabaseStatisticsService {

    @Autowired
    private DatabaseStatisticsRepository databaseStatisticsRepository;

    public TableStatisticsView getTableStatistics(String tableName) {
        Assert.hasLength(tableName, "tableName can't be null or empty");

        return databaseStatisticsRepository.getStatisticsForTable(tableName.toUpperCase());
    }

    public Map<String, TableStatisticsView> getDatabaseTablesStatistics() {
        return databaseStatisticsRepository.getTableStatistics();
    }
}
