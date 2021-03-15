package com.restdb.dbapi.db_statistics;

import com.restdb.dbapi.db_statistics.dto.TableStatisticsView;

import java.util.Map;

public interface DatabaseStatisticsRepository {
    TableStatisticsView getStatisticsForTable(String tableName);
    Map<String, TableStatisticsView> getTableStatistics();
}
