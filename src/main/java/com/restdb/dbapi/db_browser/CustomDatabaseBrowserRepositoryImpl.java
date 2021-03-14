package com.restdb.dbapi.db_browser;

import com.restdb.dbapi.db_browser.model.TableColumnsInfoView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.restdb.dbapi.constant.ConstraintType.getConstraintTypeByDefinition;

@Component
public class CustomDatabaseBrowserRepositoryImpl implements CustomDatabaseBrowserRepository{

    @Autowired
    @Qualifier("jdbcCustom")
    private JdbcTemplate jdbcTemplate;

    @Override
    //todo user might not have privileges to list available tables. Would be nice to check rights first.
    // if no rights throw an exception
    public List<String> getTables() {
        String sql = "SELECT DISTINCT OBJECT_NAME FROM USER_OBJECTS WHERE OBJECT_TYPE = 'TABLE'";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString(1));
    }

    @Override
    //todo user might not have privileges to list available schemas. Would be nice to check rights first.
    // if no rights throw an exception
    public List<String> getSchemas() {
        String sql = "SELECT USERNAME AS SCHEMA_NAME FROM SYS.ALL_USERS ORDER BY USERNAME";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString(1));
    }

    public List<TableColumnsInfoView> getTableColumns(String tableName) {
        String sql = "select a.column_name,a.data_type,a.data_length, a.nullable, b.CONSTRAINT_NAME, b.constraint_type" +
                        " from ALL_TAB_COLS a" +
                        " left join " +
                        "  (select acc.CONSTRAINT_NAME, acc.table_name, acc.column_name, ac.constraint_type" +
                        "  from ALL_CONS_COLUMNS acc, all_constraints ac" +
                        "  where acc.OWNER = (select user from dual)" +
                        "  and ac.OWNER = (select user from dual)" +
                        "  and acc.CONSTRAINT_NAME not like '%SYS%' " +
                        "  and acc.constraint_name = ac.constraint_name" +
                        "  ) B on a.COLUMN_NAME=B.COLUMN_NAME and a.TABLE_NAME=B.TABLE_NAME" +
                        " WHERE a.OWNER= (select user from dual)" +
                        " and a.column_name not like '%SYS_%'" +
                        " and a.table_name = ?" +
                        " order by column_id";

        return jdbcTemplate.query(sql, new TableColumnsDataViewRowMapper(), tableName.toUpperCase());
    }

    private final class TableColumnsDataViewRowMapper implements RowMapper<TableColumnsInfoView> {

        public TableColumnsDataViewRowMapper() {}

        @Override
        public TableColumnsInfoView mapRow(ResultSet rs, int rowNum) throws SQLException {
            String columnName = rs.getString(1);
            String dataType = rs.getString(2);
            String dataLength = rs.getString(3);
            String nullable = rs.getString(4);
            String constraintName = rs.getString(5);
            String constraintDefinition = rs.getString(6);

            return new TableColumnsInfoView(columnName, dataType, dataLength, nullable, constraintName, getConstraintTypeByDefinition(constraintDefinition));
        }
    }

}
