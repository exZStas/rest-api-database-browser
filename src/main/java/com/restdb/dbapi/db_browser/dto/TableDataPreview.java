package com.restdb.dbapi.db_browser.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class TableDataPreview {
    //key - db column name, value - db record
    private Map<String, String> columnNameRecordMap;

}
