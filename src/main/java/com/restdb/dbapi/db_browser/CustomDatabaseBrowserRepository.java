package com.restdb.dbapi.db_browser;

import com.restdb.dbapi.db_browser.dto.TableDataPreview;
import com.restdb.dbapi.db_browser.model.TableColumnsInfoView;

import java.util.List;

public interface CustomDatabaseBrowserRepository {
    List<String> getTables();
    List<String> getSchemas();
    List<TableColumnsInfoView> getTableColumns(String tableName);
    List<TableDataPreview> getTableDataPreview(String tableName);
}
