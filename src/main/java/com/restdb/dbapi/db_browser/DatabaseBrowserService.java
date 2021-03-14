package com.restdb.dbapi.db_browser;

import com.restdb.dbapi.db_browser.dto.TableDataPreview;
import com.restdb.dbapi.db_browser.model.TableColumnsInfoView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
        Assert.hasLength(tableName, "tableName can't be null or empty");

        return databaseBrowserRepository.getTableColumns(tableName.toUpperCase());
    }

    public Map<String, List<String>> getTableDataPreview(String tableName) {
        Assert.hasLength(tableName, "tableName can't be null or empty");

        List<TableDataPreview> tableDataPreview = databaseBrowserRepository.getTableDataPreview(tableName.toUpperCase());
        Map<String, List<String>> columnRecordOrdered = new LinkedHashMap<>();

        //to preserve columns db order. Approach with flatMap -> grouping in collectors doesn't preserve order
        tableDataPreview.forEach(tdp -> {
            Map<String, String> columnNameRecordMap = tdp.getColumnNameRecordMap();
            //entry always contains singly value only
            for(Map.Entry<String, String> entry: columnNameRecordMap.entrySet()) {
                List<String> res = columnRecordOrdered.computeIfAbsent(entry.getKey(), k -> new ArrayList<>());
                res.add(entry.getValue());
            }
        });

        return columnRecordOrdered;
    }
}
