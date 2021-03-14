package com.restdb.dbapi.db_browser.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class TableDataPreview {
    private Map<String, String> columnNameRecordMap;

}
