package com.restdb.dbapi.db_browser;

import com.restdb.dbapi.db_browser.model.TableColumnsInfoView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseBrowserService {

    @Autowired
    private CustomDatabaseBrowserRepository databaseBrowserRepository;

    public List<String> getTables() {
        return databaseBrowserRepository.getTables();
    }

    public List<String> getSchemas() {
        return databaseBrowserRepository.getSchemas();
    }

    public List<TableColumnsInfoView> getTableColumnsInfoView(String tableName) {
        return databaseBrowserRepository.getTableColumns(tableName);
    }
}
