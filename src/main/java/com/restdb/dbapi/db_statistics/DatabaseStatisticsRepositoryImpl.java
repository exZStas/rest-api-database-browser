package com.restdb.dbapi.db_statistics;

import com.restdb.dbapi.db_browser.DatabaseBrowserRepository;
import com.restdb.dbapi.db_statistics.dto.TableStatisticsView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class DatabaseStatisticsRepositoryImpl implements DatabaseStatisticsRepository {

    private final static String FLYWAY_SCHEMA_HISTORY = "flyway_schema_history";

    @Autowired
    @Qualifier("jdbcCustom")
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DatabaseBrowserRepository databaseBrowserRepository;

    private boolean isTableExists(String tableName) {
        String sql = "select count(*) from all_tables where table_name = ?";
        Integer result = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> rs.getInt(1), tableName);
        return result != null && result > 0;
    }

    public Map<String, TableStatisticsView> getTableStatistics() {
        List<String> tableNames = databaseBrowserRepository.getTables();
        Map<String, TableStatisticsView> tableNameTableStatisticsMap = new HashMap<>();
        for(String tableName: tableNames) {
            //special table, no need to process it
            if(!FLYWAY_SCHEMA_HISTORY.contains(tableName)) {
                TableStatisticsView statisticsForTable = getStatisticsForTable(tableName);
                tableNameTableStatisticsMap.put(tableName, statisticsForTable);
            }
        }

        return tableNameTableStatisticsMap;
    }

    public TableStatisticsView getStatisticsForTable(String tableName) {
        String sql = String.format("with " +
                "records_count as (select count(*) as records_count from %s), " +
                "attributes_count as " +
                "(" +
                "    select count(column_name)" +
                "    from all_tab_cols" +
                "    where owner = (select user from dual)" +
                "    and table_name = '%s' and column_name not like '%%SYS_%%'" +
                ") " +
                "select * from records_count,attributes_count", tableName, tableName);

        return jdbcTemplate.queryForObject(sql, new TableStatisticsViewRowMapper());
    }

    private final class TableStatisticsViewRowMapper implements RowMapper<TableStatisticsView> {

        public TableStatisticsViewRowMapper() {}

        @Override
        public TableStatisticsView mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long recordsCount = rs.getLong(1);
            Long tableAttributesCount = rs.getLong(2);

            return new TableStatisticsView(recordsCount, tableAttributesCount);
        }
    }
}
